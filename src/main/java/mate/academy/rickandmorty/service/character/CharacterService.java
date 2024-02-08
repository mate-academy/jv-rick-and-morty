package mate.academy.rickandmorty.service.character;

import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharactersDto;

public interface CharacterService {
    CharacterDto getRandomCharacter();

    CharactersDto getCharactersBySearchString(String searchString);
}
