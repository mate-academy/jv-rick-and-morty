package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAllCharacters(List<Character> characters) {
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterInternalDto getRandomCharacter() {
        List<Character> all = characterRepository.findAll();
        Character character = all.get(new Random().nextInt(all.size()));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterInternalDto> getCharactersContainsName(String name) {
        return characterRepository.findCharacterByNameContains(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public long count() {
        return characterRepository.count();
    }

}
