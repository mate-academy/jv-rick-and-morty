package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;

public interface MovieCharacterService {
    MovieCharacterResponseDto getRandom();

    List<MovieCharacterResponseDto> findByNameLike(String namepart);
}
