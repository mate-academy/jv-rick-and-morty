package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;

public interface RickAndMortyClient {

    List<SeriesCharacterDto> getCharacters(int page);

    int getCharacterPageCount();
}
