package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();
    
    List<CharacterDto> getByName(String name);
    
    void saveAllWithBounds(List<CharacterDataDto> data);
}
