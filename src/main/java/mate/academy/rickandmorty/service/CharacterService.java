package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    CharacterDto findAnyCharacter();

    List<CharacterDto> findByName(String name);

    List<Character> saveCharacters(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList);

    List<Character> convertListOfExternalDtoToModel(
            List<CharacterFromExternalApiDto> characterFromExternalApiDtoList);

}
