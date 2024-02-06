package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterService characterService;

    @PostConstruct
    public void initializeCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response
                    = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            CharacterResponseDataDto dtos = objectMapper.readValue(
                    response.body(), CharacterResponseDataDto.class
            );

            characterService.saveAll(dtos.results());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
