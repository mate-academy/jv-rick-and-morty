package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    CharacterResponseDto getRandomCharacter();

    List<CharacterResponseDto> findByNamePart(Pageable pageable, String namePart);
}
