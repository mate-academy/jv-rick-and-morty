package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.dto.api.ApiCharacterResponseDto;

public interface ClientService {
    List<ApiCharacterResponseDto> getAllCharacters();
}
