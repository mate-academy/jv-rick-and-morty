package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Character;
import java.util.List;


public interface CharacterService {
    List<Character> getCharactersByName(String name);
    Character getRandomCharacter();
}
