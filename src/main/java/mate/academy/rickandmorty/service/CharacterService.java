package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterResponseDto getById(Long id);

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> getCharactersByName(String name);

    void saveAllCharacters(List<CharacterResponseDto> characterResponseDtos);

}
