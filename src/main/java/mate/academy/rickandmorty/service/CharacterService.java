package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void loadingAndSavingCharacters();

    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharactersWhoseNameContains(String name);
}
