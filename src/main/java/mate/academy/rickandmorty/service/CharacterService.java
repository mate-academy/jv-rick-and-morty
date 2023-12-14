package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;

public interface CharacterService {
    CharacterInternalDto getRandomCharacter();

    List<CharacterInternalDto> findByName(String name);

}
