package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?name=%s";
    private final ObjectMapper objectMapper;

    public List<CharacterDto> getCharacterByName(String name) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = String.format(BASE_URL, name);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDataDto dataDto =
                    objectMapper.readValue(
                            response.body(),
                            CharacterResponseDataDto.class
                    );
            List<CharacterDto> characterDtoList = dataDto.getResults();
            for (CharacterDto character : characterDtoList) {
                character.setExternalId(character.getId());
            }
            return characterDtoList;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterDto getRandomCharacter() {
        List<CharacterDto> allCharacters = getCharacterByName("");
        Random random = new Random();
        return allCharacters.get(random.nextInt(allCharacters.size()));
    }
}
