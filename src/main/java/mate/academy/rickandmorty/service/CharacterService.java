package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import java.util.List;

public interface CharacterService {
    InternalCharacterDto getRandomCharacter();

    List<InternalCharacterDto> getCharactersByName(String name);
}
