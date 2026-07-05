package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<Character> saveAll(CharacterResponseDataDto responseDataDto);

    CharacterDto getRandomCharacter();

    List<CharacterDto> findByName(String name);
}
