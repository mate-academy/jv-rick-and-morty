package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void downloadAllCharacter();

    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);

}
