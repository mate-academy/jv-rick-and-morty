package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;

public interface ApiService {
    List<CharacterResponseDto> fetchDataFromApi();

    void fetchDataToBd();
}
