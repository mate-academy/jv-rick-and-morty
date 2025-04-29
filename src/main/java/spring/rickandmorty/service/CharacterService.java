package spring.rickandmorty.service;

import java.util.List;
import spring.rickandmorty.model.Character;

public interface CharacterService {
    Character getRandomCharacter();

    List<Character> searchCharactersByName(String name);
}
