package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface CharacterService {
    void saveAllCharacters();

    CharacterDto getRandomCharacter();

    List<CharacterDto> findCharactersByName(String name);
}
