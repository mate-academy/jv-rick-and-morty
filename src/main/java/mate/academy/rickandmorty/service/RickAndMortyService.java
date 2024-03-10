package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface RickAndMortyService {
    void save(ExternalCharacterDto responseDto);

    CharacterDto getById(Long id);

    List<CharacterDto> getByName(String name);

    public long getNumberAllCharacters();

}
