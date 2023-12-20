package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.character.dtos.CharacterDto;

public interface CharacterService {
    List<CharacterDto> findAllByPartName(String namePart);

    CharacterDto getRandomCharacter();
}
