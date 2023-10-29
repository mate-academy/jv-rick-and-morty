package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private final ObjectMapper objectMapper;
    @Value("${rick-and-morty-api-get-all-url}")
    private String getAllBaseUrl;

    public InternalCharListDto getAllCharacters(Integer page) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = UriComponentsBuilder
                .fromHttpUrl(getAllBaseUrl)
                .queryParam("page", page)
                .toUriString();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(
                    response.body(),
                    InternalCharListDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
