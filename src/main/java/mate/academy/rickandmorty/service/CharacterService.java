package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    CharacterResponseDto getRandom();

    List<CharacterResponseDto> search(String name);

    List<Character> saveAll(List<ExternalCharacterDto> externalCharacterDtos);
}
