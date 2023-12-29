package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getCharacterById(Long id);

    List<CharacterDto> getPatternNameCharacters(String pattern);

    void loadAllCharacters();
}
