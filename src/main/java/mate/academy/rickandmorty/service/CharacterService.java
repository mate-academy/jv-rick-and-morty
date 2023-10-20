package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    List<Character> downloadAllCharacters();

    CharacterDto findByRandomId();

    void deleteAllCharacters();

    List<CharacterDto> searchCharactersByName(CharacterSearchParametersDto searchParametersDto,
                                              Pageable pageable);

    List<CharacterDto> getAll(Pageable pageable);
}
