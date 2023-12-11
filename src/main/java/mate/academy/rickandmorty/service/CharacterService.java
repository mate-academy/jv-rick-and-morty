package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> getCharactersByName(String name, Pageable pageable);
}
