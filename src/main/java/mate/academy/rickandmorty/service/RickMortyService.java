package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.models.Character;

public interface RickMortyService {
    List<Character> getAllCharactersByName(String name);

    Character getRandomCharacter();
}
