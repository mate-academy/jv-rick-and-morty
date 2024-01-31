package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterApiDto;
import mate.academy.rickandmorty.model.Character;

public interface RickAndMortyApiService {
    CharacterApiDto getRandomCharacter();

    List<Character> findCharacterBySearchString(String searchString);
}
