package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;

public interface CharacterService {
    CharacterResponseDto getRandom();
    List<CharacterResponseDto> search(String name);
}
