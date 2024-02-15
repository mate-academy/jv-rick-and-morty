package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;

import java.util.List;

public interface RickAndMortyService {
    RickAndMortyCharacterDto getRandomCharacter();

    List<RickAndMortyCharacterDto> findCharactersByNameContaining(String searchString);
}
