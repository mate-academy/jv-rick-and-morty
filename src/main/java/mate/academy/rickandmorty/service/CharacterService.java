package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    void saveAll();

    SeriesCharacterResponseDto getRandomCharacter();

    List<SeriesCharacterResponseDto> findCharactersByName(String name, Pageable pageable);
}
