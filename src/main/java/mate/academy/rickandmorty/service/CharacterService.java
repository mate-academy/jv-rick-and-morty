package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CreateCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAll(List<CreateCharacterDto> characterDtoList);

    CharacterDto getRandomCharacter();

    List<CharacterDto> findByName(String name);
}
