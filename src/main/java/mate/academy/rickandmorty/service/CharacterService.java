package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.external.CharacterResultDto;
import mate.academy.rickandmorty.model.internal.Character;

public interface CharacterService {
    void saveCharactersToDB();

    Character convertDtoToCharacter(CharacterResultDto characterResultDto);

    Character getRandomCharacter();

    List<Character> searchCharactersByName(String name);
}
