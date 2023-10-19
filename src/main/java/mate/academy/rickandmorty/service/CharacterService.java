package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;

import java.util.List;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    void saveAll(List<CharacterResponseDto> listDto);

    List<CharacterDto> findByName(String name);
}
