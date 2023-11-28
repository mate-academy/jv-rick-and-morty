package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponse;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character/";

    private final ObjectMapper objectMapper;
    private final CharacterService characterService;
    private final HttpClient httpClient;

    public void makeRequestAllCharacters() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(CHARACTERS_URL))
                    .build();
            HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());

            List<CharacterEntity> characterResponse = objectMapper
                    .readValue(response.body(), CharacterResponse.class)
                    .getResults();

            characterService.saveAll(characterResponse);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterEntity makeRequestRandomCharacter() {
        List<Long> characterIds = characterService
                .getAll()
                .stream()
                .map(CharacterEntity::getId)
                .toList();

        return characterService
                .findById(characterIds
                        .get(new Random()
                                .nextInt(characterIds.size())));
    }

}
