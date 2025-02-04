package mate.academy.rickandmorty.service.internal;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CreateCharacterRequestDto;

public interface CharacterService {
    CharacterDto save(CreateCharacterRequestDto characterRequestDto);

    CharacterDto getRandomCharacter();

    List<CharacterDto> searchCharactersByName(String name);
}
