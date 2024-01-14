package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;

public interface MovieCharacterService {
    void saveAll(List<ApiCharacterDto> characterDtos);

    MovieCharacterResponseDto getRandomCharacter();

    List<MovieCharacterResponseDto> findAllByNameContains(String namePart);
}
