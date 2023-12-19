package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;

public interface CharacterService {
    CharacterResponseDto save(CharacterDto characterDto);

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> getCharactersByName(String name);
}
