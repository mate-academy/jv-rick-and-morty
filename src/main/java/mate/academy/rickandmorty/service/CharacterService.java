package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getWikiAboutRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);
}
