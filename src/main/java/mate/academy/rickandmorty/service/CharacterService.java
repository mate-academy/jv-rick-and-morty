package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;

public interface CharacterService {
    void saveAllCharacters();

    CharacterDto getRandomCharacter()throws EntityNotFoundException;

    List<CharacterDto> findCharactersByName(String name);
}
