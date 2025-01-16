package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterConversionDto;

public interface CharacterService {

    CharacterConversionDto getRandomCharacter();

    List<CharacterConversionDto> searchCharacters(String searchParameter);
}
