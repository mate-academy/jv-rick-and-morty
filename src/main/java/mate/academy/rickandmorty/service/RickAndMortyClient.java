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
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExternalCharacterDto> fetchAllCharacters() {
        List<ExternalCharacterDto> characters = new ArrayList<>();
        String url = BASE_URL;

        while (url != null && !url.isEmpty()) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request,
                        HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    throw new RuntimeException("API error response code: "
                            + response.statusCode());
                }

                ApiResponseDto apiResponse = objectMapper.readValue(response.body(),
                        ApiResponseDto.class);

                if (apiResponse.results() != null) {
                    characters.addAll(apiResponse.results());
                }
                url = apiResponse.info() != null ? apiResponse.info().next() : null;

                if (url != null) {
                    Thread.sleep(800);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't fetch data from Rick and Morty API", e);
            }
        }
        return characters;
    }
}

