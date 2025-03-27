package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final int ONE = 1;
    private static final Random RANDOM = new Random();
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final RickMortyClient rickMortyClient;

    @PostConstruct
    public void loadAllCharactersAtStartup() {
        loadAllCharactersFromExternal();
    }

    public void loadAllCharactersFromExternal() {
        List<CharacterApiResponseDto> externalCharacters = rickMortyClient
                .loadAllCharactersFromExternal();

        List<Character> characters = externalCharacters.stream()
                .map(characterMapper::toEntity)
                .filter(character -> !characterRepository
                        .existsByExternalId(character.getExternalId()))
                .collect(Collectors.toList());

        characterRepository.saveAll(characters);
    }

    public CharacterResponseDto getRandomWiki() {
        long characterLoadId = RANDOM.nextLong(ONE, characterRepository.count());
        return characterMapper.toResponseDto(characterRepository.findById(characterLoadId)
                .orElseThrow(() -> new CharacterNotFoundException("Character not found with id "
                        + characterLoadId)));
    }

    @Transactional
    public List<CharacterResponseDto> getCharactersWithNameLike(String name) {
        List<Character> characters = characterRepository.findByNameContainingIgnoreCase(name);
        return characters.stream()
                .map(characterMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
