package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParams;

public interface CharacterService {
    List<CharacterDto> saveAll();

    List<CharacterDto> search(CharacterSearchParams searchParams);

    CharacterDto getRandomCharacter();
}
