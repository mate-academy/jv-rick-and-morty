package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.InternalCharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InternalCharacterServiceImpl implements InternalCharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper charactersMapper;

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        List<Character> characters = characterRepository.findByNameContaining(name);
        return charactersMapper.listToDto(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Character characters = characterRepository.findById(getRandomLong())
                .orElseThrow(() -> new RuntimeException(
                        "An error while pulling a random character"));
        return charactersMapper.toDto(characters);
    }

    private Long getRandomLong() {
        return new Random().nextLong(characterRepository.count());
    }
}
