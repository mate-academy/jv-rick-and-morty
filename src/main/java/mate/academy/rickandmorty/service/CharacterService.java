package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {

    void saveAllCharacters(List<Character> characters);

    CharacterInternalDto getRandomCharacter();

    List<CharacterInternalDto> getCharactersContainsName(String name);
}
