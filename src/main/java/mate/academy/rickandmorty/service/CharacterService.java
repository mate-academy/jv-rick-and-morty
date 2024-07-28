package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;

public interface CharacterService {
    OutputCharacterResponseDto findCharacterByRandomId();

    List<OutputCharacterResponseDto> findCharactersByName(String characterName);
}
