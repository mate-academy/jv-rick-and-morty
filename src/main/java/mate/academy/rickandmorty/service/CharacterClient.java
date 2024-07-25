package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.ArrayCharactersResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/%s";
    private static final String PREFIX_FOR_URL = "?";

    private final ObjectMapper objectMapper;

    public CharacterClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CharacterResponseDto getCharacters(String searchParams) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted(searchParams);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            return objectMapper.readValue(
                    response.body(),
                    CharacterResponseDto.class
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CharacterResponseDto> getCharactersByName(String name) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String correctForm = PREFIX_FOR_URL + name;
        String url = BASE_URL.formatted(correctForm);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        List<CharacterResponseDto> result = new ArrayList<>();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            String responseBody = response.body();
            ArrayCharactersResponseDto arrayCharacters = objectMapper.readValue(
                    responseBody,
                    ArrayCharactersResponseDto.class
            );
            return arrayCharacters.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
