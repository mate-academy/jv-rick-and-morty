package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    public Character getRandomCharacter() {
        List<Long> allIds = characterRepository.findAllIds();
        if (allIds.isEmpty()) {
            return null;
        }
        Long randomId = allIds.get(random.nextInt(allIds.size()));
        return characterRepository.findById(randomId).orElse(null);
    }

    public List<Character> findAllByNameContains(String namePart) {
        return characterRepository.findByNameContainingIgnoreCase(namePart);
    }
}
