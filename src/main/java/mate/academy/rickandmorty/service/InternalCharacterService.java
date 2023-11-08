package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharactersDto;

public interface InternalCharacterService {
    List<CharactersDto> getCharactersByName(String name);

    CharactersDto getRandomCharacter();
}
