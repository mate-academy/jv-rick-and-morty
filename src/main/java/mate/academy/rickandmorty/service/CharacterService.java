package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<Character> getAll();

    List<CharacterDto> getCharactersByName(String name);

    Character saveRandomCharacter();
}
