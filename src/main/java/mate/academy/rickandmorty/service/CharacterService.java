package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.MovieCharacter;

public interface CharacterService {
    void saveAll(List<MovieCharacter> characters);

    List<MovieCharacter> findAllByName(String name);

    MovieCharacter getRandomCharacter();
}
