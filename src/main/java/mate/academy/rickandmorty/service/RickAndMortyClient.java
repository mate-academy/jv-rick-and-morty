package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final long DELAY_MS = 800;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExternalCharacterDto> fetchAllCharacters() {
        List<ExternalCharacterDto> characters = new ArrayList<>();
        String url = BASE_URL;

        while (url != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        request, HttpResponse.BodyHandlers.ofString());

                ApiResponseDto apiResponse = objectMapper.readValue(
                        response.body(), ApiResponseDto.class);

                if (apiResponse != null && apiResponse.getResults() != null) {
                    characters.addAll(apiResponse.getResults());
                    url = apiResponse.getInfo() != null ? apiResponse.getInfo().getNext() : null;
                } else {
                    url = null;
                }

                if (url != null) {
                    Thread.sleep(DELAY_MS);
                }

            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Failed to fetch characters from external API", e);
            }
        }
        return characters;
    }
}


