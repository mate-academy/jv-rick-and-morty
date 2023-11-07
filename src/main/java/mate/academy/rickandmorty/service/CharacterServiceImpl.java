package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepository repository;
    private CharacterMapper mapper;

    @Override
    public List<CharacterDto> getAll() {
        List<Character> characters = repository.findAll();
        return mapper.map(characters);
    }

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        List<Character> characters = repository.findByNameContaining(name);
        List<CharacterDto> characterDtos = mapper.map(characters);
        return characterDtos;
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long bound = repository.count();
        long randomLong = new Random().nextLong(bound);
        Character character = repository.findById(randomLong)
                .orElseThrow(() -> new RuntimeException(
                        "Occurred an error while pulling random character"));
        return mapper.characterToCharacterDto(character);
    }
}
