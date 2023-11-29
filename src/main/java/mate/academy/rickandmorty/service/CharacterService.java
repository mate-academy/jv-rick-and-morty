package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharactersByName(String name);

    List<CharacterEntity> saveAll(List<CharacterDto> characters);

}
