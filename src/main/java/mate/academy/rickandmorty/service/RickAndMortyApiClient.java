package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyApiClient {
    private static final String API_URL = "https://rickandmortyapi.com/api/character/?page=%s";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public RickAndMortyApiResponseDto getCharacterInfo(Long page) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(API_URL, page)))
                .build();
        try {
            HttpResponse<String> result = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(result.body(), RickAndMortyApiResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get characters info for page " + page, e);
        }
    }
}
