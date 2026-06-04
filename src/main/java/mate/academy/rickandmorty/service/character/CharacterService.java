package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.character.CharacterDto;

public interface CharacterService {
    CharacterDto findRandomCharacter();

    List<CharacterDto> findCharactersByName(String name);
}
