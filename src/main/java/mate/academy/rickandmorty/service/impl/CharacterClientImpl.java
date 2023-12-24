package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterResponseDto> getAllCharacters() {
        CharacterResponseDataDto characterResponseDataDto = getCharacters(BASE_URL);
        List<CharacterResponseDto> characters = characterResponseDataDto.results();
        String apiUrl = characterResponseDataDto.info().next();

        while (apiUrl != null) {
            characterResponseDataDto = getCharacters(apiUrl);
            characters.addAll(characterResponseDataDto.results());
            apiUrl = characterResponseDataDto.info().next();
        }
        return characters;
    }

    private CharacterResponseDataDto getCharacters(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Помилка отримання персонажів з API", e);
        }
    }
}
