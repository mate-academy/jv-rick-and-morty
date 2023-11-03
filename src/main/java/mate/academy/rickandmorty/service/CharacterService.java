package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    void saveAll(List<ExternalCharacterDto> characterDtos);

    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> search(Pageable pageable, String name);
}
