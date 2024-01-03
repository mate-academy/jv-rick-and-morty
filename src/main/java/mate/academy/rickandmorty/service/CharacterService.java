package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> findCharacterByName(String name, Pageable pageable);
}
