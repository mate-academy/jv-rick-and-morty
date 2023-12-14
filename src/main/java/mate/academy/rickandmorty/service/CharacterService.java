package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterDto;

import java.util.List;

public interface CharacterService {
    CharacterDto getWikiAboutRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);
}
