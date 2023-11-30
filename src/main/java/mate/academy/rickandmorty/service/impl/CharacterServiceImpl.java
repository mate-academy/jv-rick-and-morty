package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void save(Character character) {
        characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        return characterMapper.toDto(characterRepository
                .findById(random.nextLong(characterRepository.count())).orElseThrow(
                        () -> new EntityNotFoundException("Can't find random character")));
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        List<Character> charactersByName = characterRepository.findCharactersByNameContains(name);
        return charactersByName.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
