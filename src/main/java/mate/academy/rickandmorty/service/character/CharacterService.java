package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.character.CharacterDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> findByName(String name);

    void saveAllCharacters();
}
