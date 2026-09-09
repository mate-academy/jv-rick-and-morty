package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;

public interface RickAndMortyClient {
    List<RickAndMortyCharacterDto> getAllCharacters();
}
