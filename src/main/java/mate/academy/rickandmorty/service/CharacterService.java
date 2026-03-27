package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;

public interface CharacterService {
    List<CharacterEntity> findAllByName(String name);

    CharacterEntity getRandom();

}
