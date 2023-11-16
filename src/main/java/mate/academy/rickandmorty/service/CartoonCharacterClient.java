package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;

public interface CartoonCharacterClient {
    List<CreateCartoonCharacterRequestDto> getAllCharacters();
}
