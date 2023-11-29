package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;

public interface CharacterService {
    void syncExternalCharacter();

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> findAllByNameContains(String namePart);
}
