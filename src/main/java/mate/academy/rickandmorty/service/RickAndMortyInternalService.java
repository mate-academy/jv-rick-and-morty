package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface RickAndMortyInternalService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> getAllCharactersNameLike(String name);
}
