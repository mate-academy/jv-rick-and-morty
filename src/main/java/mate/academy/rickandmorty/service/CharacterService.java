package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;

public interface CharacterService {
    CharacterRequestDto getRandomCharacter();

    List<CharacterRequestDto> getByNameContaining(String name);
}
