package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface CharacterService {
    List<CharacterDto> findCharactersByName(String namePart);

    CharacterDto getRandomCharacter();

    void loadAllCharactersOnce();
}
