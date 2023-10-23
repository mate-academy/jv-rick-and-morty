package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.exception.CharacterExternalLoadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickNMortyClient {
    @Value("${rick-and-morty.url}")
    private String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public List<CharacterResponseDto> getAllCharacters() {
        String currentUrl = baseUrl;
        List<CharacterResponseDto> result = new ArrayList<>();
        do {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(currentUrl))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode parent = mapper.readTree(response.body());
                result.addAll(mapper.readerFor(new TypeReference<List<CharacterResponseDto>>() {
                }).readValue(parent.path("results")));
                JsonNode nextPage = parent.path("info").path("next");
                currentUrl = nextPage.isNull() ? null : nextPage.asText();
            } catch (InterruptedException | IOException e) {
                throw new CharacterExternalLoadException("External load characters failed", e);
            }
        } while (currentUrl != null);
        return result;
    }
}
