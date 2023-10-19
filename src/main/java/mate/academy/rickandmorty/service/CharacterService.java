package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;

public interface CharacterService {
    CharacterResponseDto getCharacter(Long id);
    
    void saveAll(List<CharacterInfoDto> dtos);
}
