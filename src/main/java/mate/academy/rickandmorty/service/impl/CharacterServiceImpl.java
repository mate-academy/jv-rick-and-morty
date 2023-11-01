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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CharacterServiceImpl implements CharacterService {
    private final Random random = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomIndex = random.nextLong(characterRepository.count());
        Character character = characterRepository.findById(randomIndex).orElseThrow(()
                -> new EntityNotFoundException("Can't get a random character"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharacterByNameContains(String partName) {
        return characterRepository.findCharacterByNameContains(partName).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
