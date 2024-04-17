package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;

public interface CharacterService {
    InternalCharacterDto getRandomCharacter();

    List<InternalCharacterDto> getCharactersByName(String name);
}
