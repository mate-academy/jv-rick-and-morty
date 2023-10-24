package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.AnimationCharacterResponseDto;

public interface AnimationCharacterService {
    List<AnimationCharacterResponseDto> findAllBySearchString(String searchString);

    AnimationCharacterResponseDto getRandomCharacter();
}
