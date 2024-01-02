package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface RickAndMortyApiService {
    Character getRandomCharacter();

    List<Character> findCharacterBySearchString(String searchString);
}
