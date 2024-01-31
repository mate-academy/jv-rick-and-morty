package mate.academy.rickandmorty.service;

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
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyApiDataServiceImpl implements RickAndMortyApiDataService {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private static final int PAGE_AMOUNT = 42;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void fetchDataAndSaveToDatabase() {
        try {
            for (int i = 1; i <= PAGE_AMOUNT; i++) {
                characterRepository.save(characterMapper.toModel(getCharactersByPage(i)));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not save data to the DB!", e);
        }
    }

    private CharacterApiDto getCharactersByPage(Integer page)
            throws IOException, InterruptedException {
        String url = URL + "/?page=" + page;
        return objectMapper.readValue(getResponse(url).body(),
                CharacterApiDto.class);
    }

    private HttpResponse<String> getResponse(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not send reply back!", e);
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
