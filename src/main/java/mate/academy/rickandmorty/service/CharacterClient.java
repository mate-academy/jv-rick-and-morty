package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    public static final String URL = "https://rickandmortyapi.com/api/character/%s";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Random random = new Random();

    public CharacterDto getJsonResponse() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = URL.formatted(random.nextInt(1, 20));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            return objectMapper.readValue(json,
                    CharacterDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
