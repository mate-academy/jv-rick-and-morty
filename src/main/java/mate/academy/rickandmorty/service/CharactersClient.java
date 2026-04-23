package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseResultsDto;

public interface CharactersClient {

    List<CharacterResponseResultsDto> fetchCharactersFromApi();
}
