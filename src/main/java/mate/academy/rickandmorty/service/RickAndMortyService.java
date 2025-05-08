package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import org.springframework.data.domain.Pageable;

public interface RickAndMortyService {

    RickAndMortyCharacterDto getRandomCharacter();

    List<RickAndMortyCharacterDto> getCharacterByName(String name, Pageable pageable);
}
