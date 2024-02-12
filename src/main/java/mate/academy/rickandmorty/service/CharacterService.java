package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;

public interface CharacterService {
    @PostConstruct
    List<CharacterDto> saveAll(List<CharacterRequestDto> characterRequestDtoList);

    CharacterDto getRandom();

    List<CharacterDto> getListCharactersByName(String name);
}
