package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;

import java.util.List;

public interface CharacterService {
    CharacterRequestDto getRandomCharacter();

    List<CharacterRequestDto> findByName(String name);
}
