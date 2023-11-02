package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

import java.util.List;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    void saveAll(List<CharacterResponseDto> listDto);

    List<CharacterDto> findByName(String name);
}
