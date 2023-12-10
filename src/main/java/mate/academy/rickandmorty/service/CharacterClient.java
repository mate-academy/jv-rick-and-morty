package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;

public interface CharacterClient {
    List<CharacterResultDto> getAllCharacters();
}
