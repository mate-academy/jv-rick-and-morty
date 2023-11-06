package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private CharacterClient client;
    private CharacterRepository repository;

    @PostConstruct
    private void init() {
        List<Character> characters = client.getCharacters();
        repository.saveAll(characters);
    }

    @Override
    public List<Character> getAll() {
        return repository.findAll();
    }


    @Override
    public List<Character> getCharactersByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public Character saveRandomCharacter() {
        long bound = repository.count();
        long randomLong = new Random().nextLong(bound);
        Character character = repository.findById(randomLong).orElseThrow(() -> new RuntimeException("Occurred an error while pulling random character"));
        character.setId(null);
        return repository.save(character);
    }
}
