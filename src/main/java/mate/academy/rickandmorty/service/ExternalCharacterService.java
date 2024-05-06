package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface ExternalCharacterService {
    List<Character> getAll();

    void saveAll(List<Character> characters);
}
