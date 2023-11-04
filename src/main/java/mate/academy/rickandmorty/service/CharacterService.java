package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    void saveAllToDB();

    CharacterDto getRandom();

    List<CharacterDto> searchCharacters(String name, Pageable pageable);
}
