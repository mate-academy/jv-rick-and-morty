package com.parkhomovsky.rickandmorty.service;

import com.parkhomovsky.rickandmorty.dto.CharacterResponseDto;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> getAllByNamePart(String namePart);
}
