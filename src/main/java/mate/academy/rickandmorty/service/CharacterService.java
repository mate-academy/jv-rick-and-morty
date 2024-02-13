package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterDtoWithoutExternalId;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    CharacterDto getRandom();

    List<CharacterDto> getAllByNameContains(String name, Pageable pageable);

    void saveAll(List<CharacterDtoWithoutExternalId> characterDtoList);
}
