package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface CharacterService {
    void loadDataFromApi();

    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);
}
