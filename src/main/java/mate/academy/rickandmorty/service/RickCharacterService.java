package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.RickCharacterDto;

public interface RickCharacterService {
    RickCharacterDto getRandomCharacter();

    List<RickCharacterDto> searchCharacters(String name);
}
