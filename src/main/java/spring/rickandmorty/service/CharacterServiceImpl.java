package spring.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.rickandmorty.model.Character;
import spring.rickandmorty.repository.CharacterRepository;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public spring.rickandmorty.model.Character getRandomCharacter() {
        List<Character> all = characterRepository.findAll();
        if (all.isEmpty()) {
            throw new RuntimeException("No characters found");
        }
        return all.get(new Random().nextInt(all.size()));
    }

    @Override
    public List<spring.rickandmorty.model.Character> searchCharactersByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }
}
