package mate.academy.rickandmorty.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mate.academy.rickandmorty.dto.ExternalCharacterResponse;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ExternalCharacterResponse getCharacters(int page) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL + "?page=" + page;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                     HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), ExternalCharacterResponse.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
