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
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiClient {

    private final ObjectMapper objectMapper;

    public CharacterResponseDataDto getCharactersInfo(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            CharacterResponseDataDto characterResponseDataDto =
                    objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
            return characterResponseDataDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
