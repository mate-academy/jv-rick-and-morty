package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    ExternalCharacterDto getRandomCharacter();

    List<ExternalCharacterDto> searchCharacterByName(String name, Pageable pageable);
}
