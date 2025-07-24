package mate.academy.rickandmorty.services;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    List<CharacterDto> findByName(String name);

    CharacterDto findRandomCharacter();
}
