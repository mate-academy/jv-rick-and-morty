package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;

public interface CharacterService {
    CharacterResponseDto getRandomCharacter();

    void saveAll(List<CharacterResultDto> listDto);

    List<CharacterResponseDto> findByName(String name);
}
