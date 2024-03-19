package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterLoader;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterLoader characterLoader;
    private final Random random;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterLoader characterLoader,
                                Random random) {
        this.characterRepository = characterRepository;
        this.characterLoader = characterLoader;
        this.random = random;
    }

    @PostConstruct
    public void loadDataFromExternalAPI() {
        characterLoader.loadDataFromExternalAPI();
    }

    @Override
    public List<Character> getCharactersByNames(String characters) {
        String[] characterNames = characters.split(",");
        List<Character> result = new ArrayList<>();
        for (String name : characterNames) {
            List<Character> charactersWithName = characterRepository.findByNameContaining(name.trim());
            result.addAll(charactersWithName);
        }
        return result;
    }

    @Override
    public Character getRandomCharacter() {
        long characterCount = characterRepository.count();
        if (characterCount == 0) {
            throw new IllegalStateException("No characters found in the database");
        }
        long randomIndex = random.nextInt((int) characterCount);
        Optional<Character> optionalCharacter = characterRepository.findById(randomIndex);
        return optionalCharacter.orElseThrow(() -> new RuntimeException("Character not found"));
    }
}
