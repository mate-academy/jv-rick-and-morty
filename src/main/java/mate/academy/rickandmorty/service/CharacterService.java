package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAll(List<CharacterResponseDto> responseDtos);

    CharacterDto findById(Long id);

    Long count();

    List<CharacterDto> getAllByName(String string);
}
