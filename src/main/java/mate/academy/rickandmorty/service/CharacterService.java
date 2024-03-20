package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);

    void saveAll(List<CharacterResponseDataDto> responseDataDto);
}
