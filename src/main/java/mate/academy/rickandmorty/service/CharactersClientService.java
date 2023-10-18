package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CharactersClientService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<CharacterInfoDto> loadCharacters() {
        try {
            var characterResponseDto = retrieveResponse(BASE_URL);
            var characters = new ArrayList<>(characterResponseDto.results());
            String nextPageUrl = characterResponseDto.info().next();
            while (nextPageUrl != null) {
                characterResponseDto = retrieveResponse(nextPageUrl);
                characters.addAll(characterResponseDto.results());
                nextPageUrl = characterResponseDto.info().next();
            }
            return characters;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Retrieving characters failed", e);
        }
    }

    private CharacterResponseDto retrieveResponse(String url)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(url))
                .build();
        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString()
        );
        return objectMapper.readValue(response.body(), CharacterResponseDto.class);
    }
}
