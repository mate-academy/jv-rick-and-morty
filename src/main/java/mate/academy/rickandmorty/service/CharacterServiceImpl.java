package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterDto getRandomCharacter() {
        Long index = random.nextLong(characterRepository.count());
        Character character = characterRepository.findById(index).orElseThrow(()
                -> new EntityNotFoundException("Can't get a random character"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findCharacterByNameContains(String name) {
        return characterRepository.findCharacterByNameContains(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
