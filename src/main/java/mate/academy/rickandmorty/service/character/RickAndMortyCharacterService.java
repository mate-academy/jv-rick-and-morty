package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;

public interface RickAndMortyCharacterService {
    RickAndMortyCharacter getRandomCharacter();

    List<RickAndMortyCharacter> findByNameContaining(String name);
}
