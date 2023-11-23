package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String BASE_URL = "http://localhost:8080/public/api/characters%s";
    private final ObjectMapper objectMapper;

    public Character getRandomCharacterFromDb() {
        String url = BASE_URL.formatted("/random");
        HttpRequest httpRequest
                = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> httpResponse
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), Character.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get random character " + e);
        }
    }

    public List<Character> getAllCharactersByInputParam(String name) {
        String url = BASE_URL.formatted("/by-param?name=" + name);
        HttpRequest httpRequest
                = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> httpResponse =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(),
                    new TypeReference<List<Character>>(){});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get characters by param " + name);
        }
    }
}
