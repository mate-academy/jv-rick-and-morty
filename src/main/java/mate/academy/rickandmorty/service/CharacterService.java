package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;

public interface CharacterService {
    InternalCharacterDto getRandomCharacter();

    void saveAll(List<ExternalCharacterDto> listDto);

    List<InternalCharacterDto> findByName(String name);
}
