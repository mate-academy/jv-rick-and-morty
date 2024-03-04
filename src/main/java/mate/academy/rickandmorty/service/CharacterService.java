package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> findCharactersByName(String name, Pageable pageable);
}
