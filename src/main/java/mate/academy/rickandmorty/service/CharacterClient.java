package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?name=%s";
    private static final int Characters_Per_Page = 20;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @PostConstruct
    public void fetchAndSaveCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL.formatted("");
        int page = 1;

        while (true) {
            String urlWithPage = url + "&page=" + page;
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlWithPage))
                    .build();

            try {
                HttpResponse<String> response = httpClient
                        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto dataDto =
                        objectMapper.readValue(response.body(), CharacterResponseDataDto.class);

                List<CharacterDataDto> charactersFromApi = dataDto.getResults();
                List<Character> charactersToSave = charactersFromApi.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());

                characterRepository.saveAll(charactersToSave);

                if (charactersFromApi.size() < Characters_Per_Page) {
                    break;
                }

                page++;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<CharacterDto> getCharacterByName(String name) {
        List<Character> characters = characterRepository.findByNameContaining(name);
        return characters.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CharacterDto getRandomCharacter() {
        List<Character> allCharacters = characterRepository.findAll();
        Character character = allCharacters.get(random.nextInt(allCharacters.size()));
        return toDto(character);
    }

    private Character toEntity(CharacterDataDto characterDto) {
        Character character = new Character();
        character.setExternalId(characterDto.getId());
        character.setName(characterDto.getName());
        character.setStatus(characterDto.getStatus());
        character.setGender(characterDto.getGender());
        return character;
    }

    private CharacterDto toDto(Character character) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setExternalId(character.getExternalId());
        characterDto.setName(character.getName());
        characterDto.setStatus(character.getStatus());
        characterDto.setGender(character.getGender());
        return characterDto;
    }
}
