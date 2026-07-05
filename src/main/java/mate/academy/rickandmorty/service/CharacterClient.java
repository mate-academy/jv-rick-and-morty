package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exception.CharacterLoadingException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CharacterClient {
    private static final String BASE_URL =
            "https://rickandmortyapi.com/api/character";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CharacterResponseDataDto getCharacters(int page) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("page", page)
                .toUriString();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new CharacterLoadingException("Can't get characters " + "Exception: " + e);
        }
    }
}
