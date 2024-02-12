package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String CANT_FIND_CHARACTER_BY_ID = "Can't find character by id: ";

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private long numberOfAvailableCharacters;

    @PostConstruct
    private void init() {
        this.numberOfAvailableCharacters = characterRepository.count();
    }

    @Override
    public CharacterInternalDto getRandomCharacter() {
        long randomCharactersId = ThreadLocalRandom.current().nextLong(numberOfAvailableCharacters);
        Character character = characterRepository
                .findById(randomCharactersId)
                .orElseThrow(
                        () -> new EntityNotFoundException(CANT_FIND_CHARACTER_BY_ID
                                + randomCharactersId)
                );
        return characterMapper.toCharacterDto(character);
    }

    @Override
    public List<CharacterInternalDto> findCharacterByName(String name) {
        return characterRepository.findRickAndMortyCharsByNameIsContainingIgnoreCase(name).stream()
                .map(characterMapper::toCharacterDto)
                .toList();
    }
}
