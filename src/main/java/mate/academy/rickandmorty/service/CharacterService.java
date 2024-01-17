package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    List<CharacterResponseDto> addAll(List<CharacterRequestDto> dto);

    CharacterResponseDto generateRandomCharacter();

    List<CharacterResponseDto> searchCharactersByName(String searchString, Pageable pageable);
}
