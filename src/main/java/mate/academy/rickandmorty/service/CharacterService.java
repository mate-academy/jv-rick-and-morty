package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;

public interface CharacterService {
    CharacterEntity getRandomCharacter();

    List<CharacterEntity> search(String name);
}
