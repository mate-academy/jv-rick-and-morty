package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dtoexternal.CharacterResponseDataDto;

public interface CharacterClient {
    List<CharacterResponseDataDto> initializeDatabase();
}
