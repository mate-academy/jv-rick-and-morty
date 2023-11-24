package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDataDto;
import mate.academy.rickandmorty.exception.HttpClientException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public ResponseDataDto getPage(int pageNumber) {
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
            throw new HttpClientException("Can't send HTTP request", e);
        }
    }
}
