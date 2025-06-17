package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.RnMCharacter;

public interface CharacterService {
    RnMCharacter getRandomCharacter();

    List<RnMCharacter> getCharactersContainingName(String name);
}
