package mate.academy.rickandmorty;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataFetchRunner implements CommandLineRunner {
    private final CharacterService characterService;
    private final ObjectMapper objectMapper;

    public DataFetchRunner(CharacterService characterService, ObjectMapper objectMapper) {
        this.characterService = characterService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiUrl = "https://rickandmortyapi.com/api/character";
        fetchAndSaveCharacters(httpClient, apiUrl);
    }

    private void fetchAndSaveCharacters(
                HttpClient httpClient, String apiUrl) throws IOException,
            InterruptedException {
        boolean hasMorePages = true;

        while (hasMorePages) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JsonNode root = objectMapper.readTree(responseBody);
                JsonNode resultsNode = root.get("results");

                if (resultsNode.isArray()) {
                    List<CharacterRequestDto> characters = objectMapper.convertValue(
                            resultsNode,
                            new TypeReference<>() {}
                    );
                    characterService.addAll(characters);

                    JsonNode infoNode = root.get("info");
                    JsonNode nextPage = infoNode.get("next");
                    if (nextPage != null && !nextPage.isNull()) {
                        apiUrl = nextPage.asText();
                    } else {
                        hasMorePages = false;
                    }
                } else {
                    throw new IOException("Failed to extract 'results'"
                            + " array from the API response.");
                }
            } else {
                throw new IOException("Failed to fetch data from the API."
                        + " Status code: " + response.statusCode());
            }
        }
    }
}
