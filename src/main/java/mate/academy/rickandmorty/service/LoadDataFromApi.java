package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharactersApiDto;
import mate.academy.rickandmorty.dto.external.ResponseInfoDto;
import mate.academy.rickandmorty.mapper.external.RickAndMortyApiMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadDataFromApi {
    private static final String GET_CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    private static final String PREFIX = "/?page=";
    private static final Integer START_PAGE = 1;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final RickAndMortyApiMapper rickAndMortyApiMapper;

    @PostConstruct
    private void saveAllCharacterFromApiToDB() {
        characterRepository.saveAll(getDataFromApi(getCountPageCharacters()));
    }

    private List<Character> getDataFromApi(int countPage) {
        List<Character> characterList = new ArrayList<>();

        for (int i = START_PAGE; i <= countPage; i++) {
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(GET_CHARACTERS_URL + PREFIX + i))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharactersApiDto charactersApiDtoDto = objectMapper.readValue(response.body(),
                        CharactersApiDto.class);

                List<Character> charactersL = charactersApiDtoDto.results()
                        .stream()
                        .map(rickAndMortyApiMapper::toDto)
                        .toList();

                characterList.addAll(charactersL);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return characterList;
    }

    private int getCountPageCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_CHARACTERS_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            ResponseInfoDto infoDto = objectMapper.readValue(response.body(),
                    ResponseInfoDto.class);
            return infoDto.info().getPages();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
