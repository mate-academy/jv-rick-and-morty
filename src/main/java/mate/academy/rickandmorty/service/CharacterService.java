package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;

public interface CharacterService {
    List<CharacterEntity> saveAll(List<CharacterEntity> characters);

    List<CharacterEntity> findByNameContaining(String name);

    CharacterEntity findByExternalId(String externalId);
}

