package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.stereotype.Service;

@Service
public class RickAndMortyClient {
    private static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String makeRequest() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(CHARACTERS_URL))
                    .build();
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CharacterEntity> parseCharactersJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CharacterResponse characterResponse = objectMapper
                .readValue(jsonString, CharacterResponse.class);
        return characterResponse.getResults();
    }
}
