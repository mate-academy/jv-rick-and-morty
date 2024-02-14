package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;
    private Long characterCount;

    @Override
    public CharacterDto findRandomCharacter() {
        if (characterCount == null) {
            characterCount = characterRepository.count();
        }

        Long randomId = random.nextLong(characterCount) + 1;

        Character character = characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Can't find character")
        );

        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name, Pageable pageable) {
        List<Character> characters = characterRepository
                .findByNameContainingIgnoreCase(name, pageable);
        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
