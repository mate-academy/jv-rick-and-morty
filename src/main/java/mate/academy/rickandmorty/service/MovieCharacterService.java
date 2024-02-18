package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.MovieCharacter;

public interface MovieCharacterService {
    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContainsIgnoreCase(String name);
}
