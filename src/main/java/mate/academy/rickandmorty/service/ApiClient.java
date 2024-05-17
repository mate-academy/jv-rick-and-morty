package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDataDto;

public interface ApiClient {
    List<CharacterApiResponseDto> fetchData();

    RickAndMortyResponseDataDto getCharacterData(String url);
}
