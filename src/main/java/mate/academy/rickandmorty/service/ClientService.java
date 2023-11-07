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
    private long numberOfCharacters = -1;
    private final CharacterRepository characterRepository;
    private final Random random;

    public Character getRandomCharacter() {
        if (numberOfCharacters == -1) {
            numberOfCharacters = initNumberOfCharacters();
        }

        Long randomId = random.nextLong(numberOfCharacters) + 1;
        return characterRepository.findById(randomId)
                .orElseThrow(() ->
                        new DataProcessException("Cannot find a character by id " + randomId));
    }

    public List<Character> getCharactersWithNameContaining(String sequence) {
        return characterRepository.findAllByNameContaining(sequence);
    }

    private long initNumberOfCharacters() {
        try {
            return characterRepository.findAll().size();
        } catch (Exception e) {
            throw new DataProcessException("Cannot read all characters to "
                    + "initialize a variable", e);
        }
    }
}
