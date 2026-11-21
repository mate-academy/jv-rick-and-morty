package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.InternalResponseDto;

public interface CharacterService {
    InternalResponseDto getRandomCharacter();

    List<InternalResponseDto> getCharactersByParams(String partName);

    void saveAll();
}
