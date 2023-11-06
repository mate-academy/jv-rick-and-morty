package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {
    private static final long NUMBER_OF_CHARACTERS = 20;
    @Autowired
    private Repository repository;
    private Random random = new Random();

    public Character getRandomCharacter() {
        Long randomCharacter = random.nextLong(NUMBER_OF_CHARACTERS) + 1;
        return repository.findById(randomCharacter).get();
    }

    public List<Character> getCharactersWithNameContaining(String sequence) {
        return repository.findAllByNameContaining(sequence);
    }
}
