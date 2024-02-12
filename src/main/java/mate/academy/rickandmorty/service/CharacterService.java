package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;

public interface CharacterService {

    @PostConstruct
    void insertCharactersToDB();

    CharacterDto getRandom();

    List<CharacterDto> getListCharactersByName(String name);
}
