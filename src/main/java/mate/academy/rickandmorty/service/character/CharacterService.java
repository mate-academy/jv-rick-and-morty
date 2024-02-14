package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;

public interface CharacterService {
    List<CharacterDto> search(CharacterSearchParameters searchParams);

    CharacterDto getRandomCharacter();
}
