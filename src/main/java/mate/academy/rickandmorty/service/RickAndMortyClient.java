package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String ALL_CHARACTERS = "/character";
    private final ObjectMapper objectMapper;

    public List<RickAndMortyCharacterDto> getAllCharacter() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL + ALL_CHARACTERS;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto responseDataDto =
                    objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
            return responseDataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Couldn't send httpRequest");
        }
    }
}
