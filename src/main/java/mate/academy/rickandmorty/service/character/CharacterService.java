package mate.academy.rickandmorty.service.character;

import java.util.List;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    void addListCharacter(List<RickAndMortyApiCharacterDto> characters);

    ResponseCharacterDto getRandomCharacter();

    List<ResponseCharacterDto> findAllByName(String name, Pageable pageable);

    ResponseCharacterDto findById(Long id);
}
