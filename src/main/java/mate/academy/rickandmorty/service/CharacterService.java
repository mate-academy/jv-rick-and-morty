package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterSearchParametersDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacter;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    ExternalCharacter getRandomCharacter();

    List<ExternalCharacter> searchCharacters(CharacterSearchParametersDto searchParametersDto,
                                             Pageable pageable);
}
