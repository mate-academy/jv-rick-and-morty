package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.CharacterDto;

import java.util.List;

public interface CharacterService {
    CharacterDto getCharacterById(Long id);

    List<CharacterDto> getPatternNameCharacters(String pattern);

    void loadAllCharacters();
}
