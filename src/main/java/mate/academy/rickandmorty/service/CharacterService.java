package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseInfoDto;
import mate.academy.rickandmorty.dto.internal.ResponceCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${external.api.url:https://rickandmortyapi.com/api/character}")
    private String externalApiUrl;

    @PostConstruct
    public void initializeDatabase() {
        if (characterRepository.count() == 0) {
            int page = 1;
            List<Character> allCharacters = new ArrayList<>();

            try {
                String next = externalApiUrl + "?page=" + page;
                while (next != null) {
                    CharacterResponseInfoDto response = restTemplate.getForObject(next, CharacterResponseInfoDto.class);

                    if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
                        break;
                    }

                    for (CharacterDto characterDto : response.getResults()) {
                        Character character = characterMapper.toEntity(characterDto);
                        allCharacters.add(character);
                    }
                    next = response.getInfo().getNext();
                }
                characterRepository.saveAll(allCharacters);
            } catch (HttpClientErrorException e) {
                System.err.println("Error fetching data from API: " + e.getMessage());
            }
        }
    }


    public ResponceCharacterDto getRandomCharacter() {
        long totalNumOfCharacters = 826L;
        long randomId = new Random().nextLong(totalNumOfCharacters) + 1;

        try {
            String url = externalApiUrl + "/" + randomId;
            Character character = restTemplate.getForObject(url, Character.class);
            if (character == null) {
                throw new IllegalArgumentException("Character not found with id: " + randomId);
            }
            return characterMapper.toDto(character);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Error fetching character with id " + randomId, e);
        }
    }

    public List<ResponceCharacterDto> searchCharactersByName(String name) {
        String url = externalApiUrl + "?name=" + name;

        try {
            CharacterResponseInfoDto[] characters = restTemplate.getForObject(url, CharacterResponseInfoDto[].class);
            if (characters == null || characters.length == 0) {
                return List.of();
            }
            return List.of(characters).stream()
                    .map(characterMapper::fromInfoToResponse)
                    .toList();
        } catch (HttpClientErrorException e) {
            System.err.println("Error searching characters by name: " + e.getMessage());
            return List.of();
        }
    }
}
