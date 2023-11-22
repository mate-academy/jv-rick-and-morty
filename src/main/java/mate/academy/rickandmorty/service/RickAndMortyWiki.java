package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface RickAndMortyWiki {
    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> findAllByName(String name, Pageable pageable);
}
