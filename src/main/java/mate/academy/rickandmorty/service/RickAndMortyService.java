package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterShortInfoDto;

public interface RickAndMortyService {
    CharacterShortInfoDto getRandomCharacter();

    List<CharacterShortInfoDto> findByNameContainingIgnoreCase(String names);
}
