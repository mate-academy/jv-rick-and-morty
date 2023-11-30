package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void save(Character character);

    CharacterDto getRandomCharacter();

    List<CharacterDto> findCharactersByName(String name);
}
