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
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    public List<CharacterResultsDto> getCharacters() {
        List<CharacterResultsDto> allCharacters = new ArrayList<>();
        String nextUrl = URL;

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            while (nextUrl != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextUrl))
                        .build();

                HttpResponse<String> response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );

                CharacterResponseDto characterResponseDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDto.class
                );

                allCharacters.addAll(characterResponseDto.getResults());
                nextUrl = characterResponseDto.getInfo().getNext();
            }

            return allCharacters;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
