package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;

public interface CharacterService {
    CharacterDto getRandomCharacterDto();

    void saveAll(List<CharacterResponseDto> characterResponseDtos);
}
