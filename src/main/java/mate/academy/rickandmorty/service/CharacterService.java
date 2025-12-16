package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {

    void save(RandMResponseDto inputDto);

    Optional<Character> findById(Long id);

    List<Character> getAllCharactersWithName(String name);

    int getNumberOfCharactersInDb();
}
