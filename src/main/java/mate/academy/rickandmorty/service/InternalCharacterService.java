package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface InternalCharacterService {
    List<CharacterDto> getCharactersByName(String name);

    CharacterDto getRandomCharacter();
}
