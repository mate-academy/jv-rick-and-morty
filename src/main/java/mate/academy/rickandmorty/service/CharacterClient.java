package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterInfo;

public interface CharacterClient {
    List<CharacterInfo> getCharacters();
}
