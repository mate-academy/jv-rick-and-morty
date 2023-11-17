package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterSearchParam;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {

    Character getRandomCharacter();

    List<Character> search(CharacterSearchParam param);
}
