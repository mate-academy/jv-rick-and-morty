package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;

public interface CharacterService {
    CharacterWikiDto generateRandomCharacter();
}
