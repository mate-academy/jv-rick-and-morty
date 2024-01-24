package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto findAnyCharacter();

    List<CharacterDto> findByName(String name);

}
