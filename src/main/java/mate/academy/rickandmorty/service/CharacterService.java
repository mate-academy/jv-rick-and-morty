package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getWikiAboutRandomCharacter();

    CharacterDto findByName(String name);
}
