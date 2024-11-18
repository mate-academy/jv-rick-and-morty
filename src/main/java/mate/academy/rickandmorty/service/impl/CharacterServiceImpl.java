package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterExternalDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RikAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final String URL = "https://rickandmortyapi.com/api/character?page=";
    private static final String URL_NEXT_FIELD = "next";
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getCharacter(Pageable pageable) {
        List<RikAndMortyCharacter> charactersList = characterRepository.findAll();
        int randomIndex = new Random().nextInt(charactersList.size());
        return characterMapper.toDto(charactersList.get(randomIndex));
    }

    @Override
    public List<CharacterDto> findCharacterByName(String name, Pageable pageable) {
        List<RikAndMortyCharacter> characters =
                characterRepository.searchRikAndMortyCharactersByNameContaining(name);
        return characters.stream()
                 .map(characterMapper::toDto)
                 .toList();
    }

    private List<CharacterExternalDto> fetchAllCharacters() {
        List<CharacterExternalDto> allCharactersList = new ArrayList<>();
        int page = 1;
        CharacterResponseDto response;

        do {
            ResponseEntity<CharacterResponseDto> apiResponse = restTemplate.getForEntity(
                    URL + page, CharacterResponseDto.class);
            response = apiResponse.getBody();
            if (response != null && response.getResults() != null) {
                allCharactersList.addAll(response.getResults());
            }
            page++;
        } while (response != null && response.getInfo().get(URL_NEXT_FIELD) != null);
        return allCharactersList;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void saveAllCharactersToDb() {
        List<CharacterExternalDto> characters = fetchAllCharacters();
        List<RikAndMortyCharacter> characterEntities = characters.stream()
                .map(characterMapper::toEntity)
                .collect(Collectors.toList());
        characterRepository.saveAll(characterEntities);
    }
}
