package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void saveAll(List<Character> characters);
}
