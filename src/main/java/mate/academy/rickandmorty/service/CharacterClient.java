package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;

public interface CharacterClient {
    List<CharacterResponseDataDto> initializeDatabase();
}
