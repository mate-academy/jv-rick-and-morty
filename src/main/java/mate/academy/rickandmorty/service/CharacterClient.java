package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface CharacterClient {
    List<Character> getCharacters();
}
