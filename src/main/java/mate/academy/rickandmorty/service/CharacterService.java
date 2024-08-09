package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;

public interface CharacterService {
    List<CharacterDto> getAll();

    CharacterDto findById(Long id);

    CharacterDto getRandomCharacter();

    List<CharacterDto> search(CharacterSearchParameters characterSearchParameters);
}
