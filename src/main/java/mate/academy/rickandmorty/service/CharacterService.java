package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalResponseDto;

public interface CharacterService {
    CharacterInternalResponseDto getRandomCharacter();

    List<CharacterInternalResponseDto> getCharactersByName(String name);

    void saveAll(List<CharacterExternalResponseDto> dtos);
}
