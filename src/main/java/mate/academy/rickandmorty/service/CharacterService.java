package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<CharacterDto> searchCharacters(String name);

    Character getCharacterById(Long id);

    CharacterDto getRandomCharacter();
}
