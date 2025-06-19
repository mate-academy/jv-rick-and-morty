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
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;

    public List<CharacterDto> getCharacters() {
        List<CharacterDto> allCharacters = new ArrayList<>();
        String url = BASE_URL;
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            while (url != null) {
                HttpRequest httpRequest = HttpRequest.newBuilder().GET()
                        .uri(URI.create(url)).build();
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponse characterResponse = objectMapper
                        .readValue(response.body(), CharacterResponse.class);
                allCharacters.addAll(characterResponse.getResults());
                url = characterResponse.getInfo().getNext();
            }
            return allCharacters;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Couldn't get characters form API", e);
        }
    }
}
