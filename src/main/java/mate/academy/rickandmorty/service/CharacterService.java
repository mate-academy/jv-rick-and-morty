package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<Character> saveAll(List<CharacterResponseDto> dtoList);

    List<Character> findAllByName(String name);

    Character findById(Long id);

    Character findRandom();
}
