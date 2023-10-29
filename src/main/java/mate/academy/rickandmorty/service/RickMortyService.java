package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.models.RickMorty;

public interface RickMortyService {
    List<RickMorty> getAllCharactersByName(String name);

    RickMorty getRandomCharacter();
}
