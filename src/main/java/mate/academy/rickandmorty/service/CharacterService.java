package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CreateCharacterRequestDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> getAllByName(String name);

    CharacterDto save(CreateCharacterRequestDto requestDto);
}
