package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.entity.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    Character save(CharactersDto dto);

    Page<CharactersDto> searchByName(String name, Pageable pageable);

    CharactersDto getRandomCharacter();

    void preloadCharacters();
}
