package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;

public interface CharacterService {

    RickAndMortyCharacter getRandomCharacter();

    List<RickAndMortyCharacter> findByNameContaining(String name);
}
