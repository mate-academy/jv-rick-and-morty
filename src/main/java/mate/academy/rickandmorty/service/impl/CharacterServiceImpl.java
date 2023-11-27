package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repo.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public List<CharacterEntity> saveAll(List<CharacterEntity> characters) {
        return characterRepository.saveAll(characters);
    }

    @Override
    public List<CharacterEntity> findByNameContaining(String name) {
        return characterRepository
                .findAll()
                .stream()
                .filter(character -> character.getName().contains(name))
                .toList();
    }

    @Override
    public CharacterEntity findByExternalId(String externalId) {
        return characterRepository.findByExternalId(externalId);
    }

}
