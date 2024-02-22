package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;

public interface CharacterService {
    CharacterInternalDto getRandomCharacterWiki();

    void saveAll(List<CharacterExternalResponseDto> characterExternalResponseDtoList);

    List<CharacterInternalDto> getCharactersByName(String name);
}
