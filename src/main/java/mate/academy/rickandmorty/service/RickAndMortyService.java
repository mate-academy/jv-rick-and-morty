package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;

public interface RickAndMortyService {
    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> findByNameLike(String params);
}
