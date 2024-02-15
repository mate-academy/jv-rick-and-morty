package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;

public interface RickAndMortyService {
    RickAndMortyCharacterDto getRandomCharacter();

    List<RickAndMortyCharacterDto> findCharactersByNameContaining(String searchString);
}
