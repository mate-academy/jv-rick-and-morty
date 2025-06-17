package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @PostConstruct
    private void save() {
        List<RnMCharacter> characters = characterClient.getRnMData()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public RnMCharacter getRandomCharacter() {
        List<RnMCharacter> characters = characterRepository.findAll();
        return characters.get(new Random().nextInt(characters.size()));
    }

    @Override
    public List<RnMCharacter> getCharactersContainingName(String name) {
        return characterRepository.findAllByNameContaining(name);
    }
}
