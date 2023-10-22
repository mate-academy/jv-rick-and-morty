package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    void saveAll(List<CharacterResponseDto> listDto);

    List<CharacterDto> findByName(String name);
}
