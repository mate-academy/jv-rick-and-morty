package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;

public interface CharacterService {
    ResponseCharacterDto getRandom();

    List<ResponseCharacterDto> searchByTemplate(String template);
}
