package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;

    public ResponseDataDto getPage(int pageNumber) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted(pageNumber);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), ResponseDataDto.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
