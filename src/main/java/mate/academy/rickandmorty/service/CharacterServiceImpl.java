package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> searchCharacters(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream().map(characterMapper::toDto)
                .toList();
    }

    @Override
    public Character getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(
                        "Character not found with id " + id));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long id = new Random().nextLong(characterRepository.count()) + 1;
        return characterMapper.toDto(getCharacterById(id));
    }
}
