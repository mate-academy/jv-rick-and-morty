package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterRickAndMortyDataResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRickAndMortyDto;

public interface RickAndMortyService {
    void save(CharacterRickAndMortyDataResponseDto responseDto);

    CharacterRickAndMortyDto getById(Long id);

    List<CharacterRickAndMortyDto> getByName(String name);

}
