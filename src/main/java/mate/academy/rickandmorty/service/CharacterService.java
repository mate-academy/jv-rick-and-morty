package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    List<CharacterDto> findByName(Pageable pageable, String name);

    CharacterDto getRandomCharacter();
}
