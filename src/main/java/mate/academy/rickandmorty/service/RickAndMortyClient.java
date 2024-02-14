package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String url = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalDto> getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto =
                    objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
            return dataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
