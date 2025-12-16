package mate.academy.rickandmorty.client.helper;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomIdGenerator {

    private static final int UPPER_BOUND_INCLUDED_RECORD = 1;

    private final CharacterRepository characterRepository;

    public Integer getRandomIdOfCharacter() {

        int bound = characterRepository.getNumberOfCharactersInDb();

        Random random = new Random();

        return random.nextInt(1,
                bound + UPPER_BOUND_INCLUDED_RECORD);
    }
}
