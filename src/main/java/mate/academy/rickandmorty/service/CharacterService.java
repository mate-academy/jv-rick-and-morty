package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;

public interface CharacterService {
    CharacterWikiDto generateRandomCharacter();

    List<CharacterWikiDto> searchAllCharactersByNameArgument(String nameArgument);
}
