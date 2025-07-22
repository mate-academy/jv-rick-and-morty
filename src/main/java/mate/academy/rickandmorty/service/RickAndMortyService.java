package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;

public interface RickAndMortyService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> search(CharacterSearchParametersDto searchParametersDto);
}
