package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface RickAndMortyClient {
    void fetchAllCharactersFromThirdPartyApi();

    CharacterDto getRandomCharacter();

    List<CharacterDto> getCharacterByName(String string);
}
