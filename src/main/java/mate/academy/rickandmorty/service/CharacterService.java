package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.LocalCharacter;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> findByNamePart(String name);
}
