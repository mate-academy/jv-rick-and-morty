package mate.academy.rickandmorty.services;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Character getRandomCharacter() {
        List<Character> allCharacters = characterRepository.findAll();

        if (allCharacters.isEmpty()) {
            return null;
        }

        int index = new Random().nextInt((int) characterRepository.count());
        return characterRepository.findAll(PageRequest.of(index, 1)).getContent().get(0);
    }

    public List<Character> findByString(String str) {
        return characterRepository.findAllByNameContainingIgnoreCase(str);
    }
}
