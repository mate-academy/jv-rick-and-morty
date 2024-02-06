package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Character;

import java.util.List;

public interface CharacterService {
    Character getRandomCharacter();

    List<Character> getCharactersByNamePart(String namePart);
}
