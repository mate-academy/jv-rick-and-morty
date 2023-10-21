package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceIml implements CharacterService {
    private static final int FIRST_CHARACTER_INDEX = 1;
    private static final int ONE = 1;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        long charactersSize = characterRepository.count();
        long characterId
                = new Random().nextLong(charactersSize - FIRST_CHARACTER_INDEX + ONE)
                + FIRST_CHARACTER_INDEX;
        return characterRepository.findById(characterId)
                .map(characterMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is no character by external id: " + characterId)
                );
    }

    @Override
    public List<CharacterDto> getCharacterByName(String string) {
        return characterMapper.toDtoList(
                characterRepository.findCharacterByNameContaining(string)
        );
    }
}
