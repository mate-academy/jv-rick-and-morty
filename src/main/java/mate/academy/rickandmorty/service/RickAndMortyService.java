package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface RickAndMortyService {
    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> getCharacterBySearchParam(String namePart, Pageable pageable);
}
