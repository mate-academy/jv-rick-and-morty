package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    Character findById(Long id);

    Character getRandomCharacter();

    List<Character> searchCharactersByNamePart(String namePart);
}
