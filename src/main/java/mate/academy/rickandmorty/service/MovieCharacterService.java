package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.MovieCharacterDto;

public interface MovieCharacterService {
    void saveAllMovieCharacters();

    MovieCharacterDto getRandomCharacter();

    List<MovieCharacterDto> findAllCharactersByName(String name);
}
