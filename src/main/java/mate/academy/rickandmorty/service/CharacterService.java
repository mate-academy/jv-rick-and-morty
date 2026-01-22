package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public CharacterEntity random() {
        List<CharacterEntity> all = repository.findAll();
        if (all.isEmpty()) {
            return null;
        }
        return all.get(ThreadLocalRandom.current().nextInt(all.size()));
    }

    public List<CharacterEntity> search(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
