package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.ResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    List<ResponseDto> getCharactersByName(String name, Pageable pageable);

    ResponseDto getRandomCharacter();
}
