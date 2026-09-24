package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface CharacterService {
    void importCharacters();

    CharacterDto getRandomCharacter();

    List<CharacterDto> findContaining(String name);
}
