package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiCharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.ExternalCharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalCharacterServiceImpl implements ExternalCharacterService {
    private static final String RICK_AND_MORTY_URL = "https://rickandmortyapi.com/api/character";
    private final List<ApiCharacterDto> allCharactersFromApi = new ArrayList<>();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    @Override
    @PostConstruct
    public void fetchDataFromApi() {
        String apiUrl = RICK_AND_MORTY_URL;
        while (!apiUrl.equals("null")) {
            List<ApiCharacterDto> apiCharactersDto = fetchApiData(apiUrl);
            allCharactersFromApi.addAll(apiCharactersDto);
            apiUrl = getNextPageUrl(apiUrl);
        }
        saveDataFromApiToDB(allCharactersFromApi);
    }

    private List<ApiCharacterDto> fetchApiData(String apiUrl) {
        try {
            HttpResponse<String> response = responseApi(apiUrl);
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.get("results");
            if (!rootNode.has("results") && !rootNode.has("info")) {
                throw new RuntimeException("JSON response does not contain"
                        + " 'results' or 'info' data.");
            }
            return objectMapper.readValue(resultsNode.toString(),
                    new TypeReference<List<ApiCharacterDto>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> responseApi(String responseApi) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(responseApi))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("HTTP Request failed with status code: "
                        + response.statusCode());
            }

            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred during the HTTP request: "
                    + e.getMessage(), e);
        }
    }

    private String getNextPageUrl(String apiUrl) {
        try {
            HttpResponse<String> response = responseApi(apiUrl);
            JsonNode infoNode = objectMapper.readTree(response.body()).get("info");
            return infoNode.get("next").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveDataFromApiToDB(List<ApiCharacterDto> apiCharactersDto) {
        List<Character> charactersList = apiCharactersDto.stream()
                .map(apiCharacterDto -> {
                    CreateCharacterDto createCharactersDto = characterMapper
                            .apiCharacterToDto(apiCharacterDto);
                    createCharactersDto.setExternalId(apiCharacterDto.getId());
                    return characterMapper.toModel(createCharactersDto);
                })
                .collect(Collectors.toList());

        characterRepository.saveAll(charactersList);
    }
}
