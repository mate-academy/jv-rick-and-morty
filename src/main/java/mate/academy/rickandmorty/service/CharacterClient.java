package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.stereotype.Service;

@Service
public class CharacterClient {
    private static final int NUMBER_OF_CHARACTERS = 20;
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;
    private final CharacterService characterService;
    private final HttpClient httpClient;
    private final CharacterMapper characterMapper;

    public CharacterClient(ObjectMapper objectMapper,
                           CharacterService characterService,
                           HttpClient httpClient,
                           CharacterMapper characterMapper) {
        this.objectMapper = objectMapper;
        this.characterService = characterService;
        this.httpClient = httpClient;
        this.characterMapper = characterMapper;
    }

    public List<CharacterResponseDataDto> getAllCharacters() {
        List<CharacterResponseDataDto> characterList = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_CHARACTERS; i++) {
            try {
                String responseBody = sendRequest(i);
                CharacterResponseDataDto characterDataDto = objectMapper
                        .readValue(responseBody, CharacterResponseDataDto.class);
                characterList.add(characterDataDto);
                CartoonCharacter characterModel = characterMapper.toModel(characterDataDto);
                characterService.add(characterModel);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error fetching or processing character data: "
                        + e.getMessage(), e);
            }
        }
        return characterList;
    }

    public CartoonCharacter getRandomCharacter() {
        Random random = new Random();
        int randomIndex = random.nextInt(NUMBER_OF_CHARACTERS);
        long unsignedLong = Integer.toUnsignedLong(randomIndex);
        return characterService.getById(unsignedLong);
    }

    private String sendRequest(int characterId) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + "/" + characterId))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
