package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;

public interface CharacterClient {
    List<ExternalCharacterDto> getCharacters();
}
