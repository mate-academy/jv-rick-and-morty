package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public void fetchDataFromApi() {
        try {
            HttpRequest initialRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(BASE_URL))
                    .build();
            HttpResponse<String> firstResponse = httpClient.send(initialRequest,
                    HttpResponse.BodyHandlers.ofString());
            Long pages = objectMapper.readValue(objectMapper
                    .readTree(firstResponse.body())
                    .get("info")
                    .get("pages")
                    .toString(), Long.class);
            for (int i = 1; i <= pages; i++) {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(new URI(BASE_URL + "?page=" + i))
                        .build();
                HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());
                saveDataToDB(response);
            }
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException("Error occurred during fetching data from API", e);
        }
    }

    private void saveDataToDB(HttpResponse<String> response) {
        characterRepository.saveAll(mapDataFromApiToObject(response)
                .stream()
                .map(characterMapper::toModel)
                .toList());
    }

    private List<CharacterApiDto> mapDataFromApiToObject(HttpResponse<String> response) {
        try {
            JsonNode results = objectMapper.readTree(response.body()).get("results");
            return objectMapper.readValue(results.toString(),
                    new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
