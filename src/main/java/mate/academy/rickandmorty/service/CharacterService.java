package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;

public interface CharacterService {

    List<InternalCharacterDto> findAllByNameContainingIgnoreCase(String name);

    InternalCharacterDto getRandomCharacter();

    public void syncExternalCharacters();
}
