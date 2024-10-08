package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;

public interface RickAndMortyService {
    List<RickAndMortyCharacter> saveAll(List<CharacterResultDto> resultDtoList);

    CharacterWikiDto getRandomCharacter();
}
