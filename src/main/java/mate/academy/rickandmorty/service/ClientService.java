package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.DataProcessException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientService {
    private static final long NUMBER_OF_CHARACTERS = 20;
    private CharacterRepository characterRepository;
    private final Random random;

    public Character getRandomCharacter() {
        Long randomId = random.nextLong(NUMBER_OF_CHARACTERS) + 1;
        return characterRepository.findById(randomId)
                .orElseThrow(() ->
                        new DataProcessException("Cannot find a character by id " + randomId));
    }

    public List<Character> getCharactersWithNameContaining(String sequence) {
        return characterRepository.findAllByNameContaining(sequence);
    }
}
