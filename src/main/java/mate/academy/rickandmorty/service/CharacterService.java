package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterDto getRandomCharacter();

    List<CharacterDto> searchCharacters(String searchTerm);

    void saveCharacter(List<CreateCharacterRequestDto> createCharacterRequestDto);
}
