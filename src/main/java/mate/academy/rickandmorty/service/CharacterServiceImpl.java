package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
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
    public CharacterResponseDto save(CharacterDto characterDto) {
        Character character = characterMapper.toEntity(characterDto);
        return characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {

        List<Character> characters = characterRepository.findAll();
        long index = new Random().nextLong(0, characters.size());
        Character character = characterRepository.findByExternalId(index).orElseThrow(()
                -> new RuntimeException("Character not found"));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name) {
        List<Character> characters = characterRepository.findAllByName(name);
        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
