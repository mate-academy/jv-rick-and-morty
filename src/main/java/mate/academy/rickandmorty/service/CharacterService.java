package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Character;

import java.util.List;

public interface CharacterService {
    List<Character> getAll();
    List<Character> getCharactersByName(String name);
    Character saveRandomCharacter();
}
