package mate.academy.rickandmorty.service.client;

import java.io.IOException;
import java.util.List;
import mate.academy.rickandmorty.dto.external.character.CharacterDto;
import mate.academy.rickandmorty.dto.external.character.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.external.character.CharactersDto;

public interface CharacterClient {
    CharactersDto getCharactersByPage(Integer page) throws IOException, InterruptedException;

    CharactersDto getCharacters() throws IOException, InterruptedException;

    List<CharacterDto> getCharactersByIds(Integer[] ids)
            throws IOException, InterruptedException;

    CharactersDto getCharactersByParams(CharacterSearchParameters characterSearchParameters)
            throws IOException, InterruptedException;
}
