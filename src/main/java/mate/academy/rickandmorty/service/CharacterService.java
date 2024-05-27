package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;

public interface CharacterService {

    List<CharacterDto> searchCharacters(String name);

    CharacterDto getRandomCharacter();

    CharacterDto save(CreateCharacterRequestDto requestDto);

}
