package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.dto.CharacterResultResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.RickAndMortyApiDataService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyApiDataServiceImpl implements RickAndMortyApiDataService {

    private static final String GET_ALL_CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void fetchDataAndSaveToDatabase() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_ALL_CHARACTERS_URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            CharacterResultResponseDto responseDto = objectMapper.readValue(
                    response.body(), CharacterResultResponseDto.class);
            List<Character> characters = convertCharacterApiDtosToModelList(responseDto);
            characterRepository.saveAll(characters);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Character> convertCharacterApiDtosToModelList(CharacterResultResponseDto dtos) {
        return dtos.results().stream()
                .map(this::toModel)
                .toList();
    }

    private Character toModel(CharacterApiDto characterApiDto) {
        Character character = new Character();
        character.setExternalId(characterApiDto.getExternalId());
        character.setGender(characterApiDto.getGender());
        character.setName(characterApiDto.getName());
        character.setStatus(characterApiDto.getStatus());
        return character;
    }
}
