package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.response.CharacterResponseDto;

public interface CharacterService {
    List<CharacterResponseDto> getCharactersByNameContaining(String name);

    CharacterResponseDto getRandomCharacter();
}
