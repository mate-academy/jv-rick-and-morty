package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.stereotype.Service;

@Service
public interface CharacterService {
    List<CharacterEntity> getAll();

    List<CharacterEntity> saveAll(List<CharacterEntity> characters);

    CharacterEntity findById(Long id);

    CharacterEntity findByExternalId(String externalId);
}

