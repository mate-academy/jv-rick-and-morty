package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharacterByName(String name, Pageable pageable);
}
