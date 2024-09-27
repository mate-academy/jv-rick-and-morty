package mate.academy.rickandmorty.external.api.service;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;

import java.util.List;

public interface ExternalCharacterService {
    void saveOrUpdate(List<CharacterResponseDto> responseDtoList);
}
