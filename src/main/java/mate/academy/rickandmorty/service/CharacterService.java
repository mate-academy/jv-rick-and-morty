package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.CharacterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    CharacterDto getRandomChar();

    Page<CharacterDto> findCharByName(String name, Pageable pageable);

    void initializeDatabase();
}
