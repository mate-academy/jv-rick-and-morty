package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAllCharacters(List<CharacterResponseDto> characters);

    CharacterDto findRandomCharacter();

    List<CharacterDto> findCharacterByName(String name);
}
