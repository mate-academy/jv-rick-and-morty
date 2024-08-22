package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.RickandmortyapiCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAll(List<RickandmortyapiCharacterDto> requestDtos);

    List<CharacterDto> getAll();

    CharacterDto getById(Long id);

    List<CharacterDto> findAllByName(String name);

    CharacterDto getRandomCharacter();
}
