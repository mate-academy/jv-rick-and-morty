package mate.academy.rickandmorty.client.helper;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomIndexGenerator {

    private static final int UPPER_BOUND_INCLUDED_RECORD = 1;

    private final GetNumberOfCharacters getNumberOfCharacters;

    private final List<Integer> drownedIndexes;

    public String randomIndexOfCharacter() {

        int bound = getNumberOfCharacters
                .getInfoAboutCountOfCharacters();

        Random random = new Random();
        int randomNumber = random.nextInt(1,
                bound + UPPER_BOUND_INCLUDED_RECORD);

        if (!drownedIndexes.contains(randomNumber)) {

            drownedIndexes.add(randomNumber);

            return String.valueOf(randomNumber);
        } else {
            randomIndexOfCharacter();
        }

        return null;
    }
}
