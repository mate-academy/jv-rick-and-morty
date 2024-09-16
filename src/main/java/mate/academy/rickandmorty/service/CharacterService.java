package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;

public interface CharacterService {
    void saveAll();

    CharacterEntity findRandom();

    List<CharacterEntity> findByName(String name);
}
