package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {

    List<CharacterResponseDto> findAll(Pageable pageable);

    CharacterResponseDto findById(Long id);

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> findAllByName(String name);

    void saveCharactersToDb();
}
