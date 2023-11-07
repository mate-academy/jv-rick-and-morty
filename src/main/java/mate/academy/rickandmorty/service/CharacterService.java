package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterSearchParam;
import mate.academy.rickandmorty.dto.external.CharacterExternalDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void saveAllCharacter(List<CharacterExternalDto> listDto);

    Character getRandomCharacter();

    List<Character> search(CharacterSearchParam param);
}
