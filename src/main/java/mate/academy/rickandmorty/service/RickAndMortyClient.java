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
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterExternalResponseDto> getCharacters() {
        List<CharacterExternalResponseDto> allCharacters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        while (url != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(
                        request, HttpResponse.BodyHandlers.ofString());

                CharacterResponseDataDto characterResponseDataDto = objectMapper.readValue(
                        response.body(), CharacterResponseDataDto.class);

                allCharacters.addAll(characterResponseDataDto.results());
                url = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't load characters from given URL");
            }
        }
        return allCharacters;
    }
}
