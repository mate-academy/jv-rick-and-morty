package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponse;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RickAndMortyApiClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public RickAndMortyApiResponse getResponse() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            RickAndMortyApiResponse apiResponse = objectMapper.readValue(response.body(),
                    RickAndMortyApiResponse.class);
            return apiResponse;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

