package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterRequestDto;

public interface RickAndMortyClientService {
    List<CharacterRequestDto> getCharacters();
}
