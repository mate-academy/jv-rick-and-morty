package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterDto;
import java.util.List;

public interface CharacterService {
    CharacterDto getRandomCharacter();
    List<CharacterDto> findCharactersByName(String name);

    void setAllCharactersFromExternalDataBaseToInternalDataBase();
}
