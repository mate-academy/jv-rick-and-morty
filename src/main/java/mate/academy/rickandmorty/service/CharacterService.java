package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.Character;

import java.util.List;


public interface CharacterService {
    Character getById(Long id);
    int getSizeOfDb();
    List<Character> getByParam(String string);
    List<CharacterDto> getDtoByParam(String string);
    CharacterDto getRandomCharacter();
}
