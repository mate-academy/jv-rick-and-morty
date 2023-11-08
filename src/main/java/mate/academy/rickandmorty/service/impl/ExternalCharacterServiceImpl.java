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
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiCharactersDto;
import mate.academy.rickandmorty.dto.internal.CreateCharactersDto;
import mate.academy.rickandmorty.mapper.CharactersMapper;
import mate.academy.rickandmorty.model.Characters;
import mate.academy.rickandmorty.repository.CharactersRepository;
import mate.academy.rickandmorty.service.ExternalCharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalCharacterServiceImpl implements ExternalCharacterService {
    private static final String RICK_AND_MORTY_API_URL =
            "https://rickandmortyapi.com/api/character";
    private final List<ApiCharactersDto> allCharactersFromApi = new ArrayList<>();
    private final CharactersRepository charactersRepository;
    private final CharactersMapper charactersMapper;

    @Override
    @PostConstruct
    public void fetchDataFromApi() {
        String apiUrl = RICK_AND_MORTY_API_URL;
        while (!apiUrl.equals("null")) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ApiCharactersDto> apiCharactersDto = null;
            try {
                JsonNode rootNode = objectMapper.readTree(responseToApi(apiUrl)
                        .body());
                JsonNode resultsNode = rootNode.get("results");
                if (!rootNode.has("results") && !rootNode.has("info")) {
                    throw new RuntimeException("JSON response does not contain 'results'"
                            + " or 'info' data.");
                }
                apiCharactersDto = objectMapper
                        .readValue(resultsNode.toString(),
                                new TypeReference<List<ApiCharactersDto>>() {
                                });
                allCharactersFromApi.addAll(apiCharactersDto);
                JsonNode infoNode = rootNode.get("info");
                JsonNode nextPageNode = infoNode.get("next");
                apiUrl = nextPageNode.asText();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        saveDataFromApiToDB(allCharactersFromApi);
    }

    private HttpResponse<String> responseToApi(String apiUrl) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred during HTTP request: "
                    + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new RuntimeException("InterruptedException occurred during HTTP request: "
                    + e.getMessage(), e);
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP Request failed with status code: "
                    + response.statusCode());
        }
        return response;
    }

    private void saveDataFromApiToDB(List<ApiCharactersDto> apiCharactersDto) {
        List<Characters> charactersList = new ArrayList<>();
        for (ApiCharactersDto apiCharacterDto : allCharactersFromApi) {
            CreateCharactersDto createCharactersDto = charactersMapper
                    .apiCharactersToDto(apiCharacterDto);
            createCharactersDto.setExternalId(apiCharacterDto.getId());
            Characters character = charactersMapper.toModel(createCharactersDto);
            charactersList.add(character);
        }
        charactersRepository.saveAll(charactersList);
    }
}
