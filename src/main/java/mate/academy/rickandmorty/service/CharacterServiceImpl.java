package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResults;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParametersDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String GET_ALL_CHARACTERS_URL = "https://rickandmortyapi.com/api/character/";
    private static final Long FIRST_ENTITY_ID = 1L;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        downloadAllCharacters();
    }

    @Override
    public List<Character> downloadAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(GET_ALL_CHARACTERS_URL))
                .build();
        CharacterResponseDataDto dataDto;
        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            dataDto = objectMapper.readValue(response.body(),
                    CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<CharacterResults> characterResults = dataDto.getResults();
        List<CharacterDto> characterDtos = characterResults.stream()
                .map(characterMapper::fromApiToDto)
                .toList();
        List<Character> characterList = characterDtos.stream()
                .map(characterMapper::toModel)
                .toList();
        deleteAllCharacters();
        return characterRepository.saveAll(characterList);
    }

    @Override
    public CharacterDto findByRandomId() {
        List<Character> allCharactersFromDb = characterRepository.findAll();
        int maxIdInDb = allCharactersFromDb.size();
        long generatedLong = new Random().nextLong(FIRST_ENTITY_ID, maxIdInDb + 1);
        return characterMapper.toDto(characterRepository.findById(generatedLong)
                .orElseThrow(() -> new NoSuchElementException("Can't find a book by id: "
                        + generatedLong)));
    }

    @Override
    public void deleteAllCharacters() {
        characterRepository.deleteAll();
    }

    @Override
    public List<CharacterDto> searchCharactersByName(
            CharacterSearchParametersDto searchParametersDto,
            Pageable pageable) {
        String stringForSearch = searchParametersDto.name();
        return characterRepository.getCharacterByNameContains(stringForSearch, pageable)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public List<CharacterDto> getAll(Pageable pageable) {
        return characterRepository.findAll()
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
