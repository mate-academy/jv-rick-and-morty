package mate.academy.rickandmorty.service;

import java.util.List;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<Character> getCharactersBySearchString(String searchString);
}
