package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;

public interface CharacterService {
    CharacterRequestDto getRandomCharacter();

    List<CharacterRequestDto> findByName(String name);

    void saveAll(List<CharacterResponseDto> characterResponseDtos);
}
