package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterRateDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface RickAndMortyService {
    void saveAll(List<CharacterRateDto> data);

    List<CharacterDto> findByName(String name);

    CharacterDto getRandomCharacter();
}
