package mate.academy.rickandmorty.service;


import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface RickAndMortyService {

    Character randomCharacterGenerated();

    List<Character> findByName(String name);
}
