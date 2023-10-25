package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void save();

    List<Character> getCharactersBySearchString(String searchString);

    ExternalCharResponseDto getRandomCharacter();
}
