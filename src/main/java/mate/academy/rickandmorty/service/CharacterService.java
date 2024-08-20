package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;

public interface CharacterService {
    void saveAll(List<ExternalCharacterDto> externalCharacterList);

    List<CharacterDto> findAll();

    Long countAll();

    CharacterDto getRandomCharacter();

    List<CharacterDto> search(CharacterSearchParameters characterSearchParameters);
}
