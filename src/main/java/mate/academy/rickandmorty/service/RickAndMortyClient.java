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
import mate.academy.rickandmorty.dto.CharacterApiRequestDto;
import mate.academy.rickandmorty.dto.CharacterDataDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyClient {
    public static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;

    public List<CharacterApiRequestDto> getCharacters() {
        List<CharacterApiRequestDto> allCharacters = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL;
        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                        .uri(URI.create(url)).build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterDataDto characterResponse = objectMapper
                        .readValue(response.body(), CharacterDataDto.class);
                allCharacters.addAll(characterResponse.getResults());
                url = characterResponse.getInfo().getNext();
            }
            return allCharacters;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Couldn't get characters from API", e);
        }
    }
}
