package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<Character> saveAllCharacters(List<CharacterDto> characterDtos);

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> searchCharacterByName(String name);
}
