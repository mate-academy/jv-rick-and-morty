package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.external.ExternalListCharacterDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClientApi {
    private static final String ERROR_MESSAGE = "Error occurred while "
            + "sending request or processing response: ";
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final String BY_ID = "%s/%d";
    private static final String BY_NAME = "%s?name=%s";

    private final HttpClient httpClient;
    private final HttpRequest.Builder requestBuilder;
    private final ObjectMapper objectMapper;

    public ExternalCharacterDto getRandomCharacter(Long randomNumber) {
        String url = BY_ID.formatted(BASE_URL, randomNumber);

        HttpRequest httpRequest = requestBuilder
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), ExternalCharacterDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(ERROR_MESSAGE + e.getMessage(), e);
        }
    }

    public List<ExternalCharacterDto> getCharactersByName(String name) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String url = String.format(BY_NAME, BASE_URL, encodedName);
        HttpRequest httpRequest = requestBuilder
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            return objectMapper
                    .readValue(response.body(), ExternalListCharacterDto.class)
                    .characters();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(ERROR_MESSAGE + e.getMessage(), e);
        }
    }
}
