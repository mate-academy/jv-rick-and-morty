package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class RickAndMortyClient {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public RickAndMortyClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CharacterResponseDto[] getCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(CHARACTER_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDto[] characterResponseDto = objectMapper.readValue(response.body(), CharacterResponseDto[].class);
            return characterResponseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
