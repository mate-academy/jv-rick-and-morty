package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void saveCharacters(List<CharacterResultsDto> characterResultsDto);

    List<Character> getCharacterByName(String name);

    void readAndSaveCharacters();

    Character getRandomCharacter();
}
