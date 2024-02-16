package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterResultDto> getAllCharacters() {
        List<CharacterResultDto> allCharacters = new ArrayList<>();
        String requestUrl = BASE_URL;
        while (requestUrl != null) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(requestUrl))
                    .build();
            try {
                HttpResponse<String> response =
                        HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto =
                        objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
                allCharacters.addAll(characterResponseDataDto.results());
                requestUrl = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to fetch data from external API"
                        + " or parse response.", e);
            }
        }
        return allCharacters;
    }
}
