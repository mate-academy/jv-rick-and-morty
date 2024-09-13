package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto save(CharacterResponseDto characterResponseDto);

    CharacterDto getRandomCharacter();

    List<CharacterDto> findAllByNamePart(String string);
}
