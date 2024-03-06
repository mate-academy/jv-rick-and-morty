package mate.academy.rickandmorty.util;

import java.util.Optional;
import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random();

    public static Long getRandomLong(long bound) {
        Optional.ofNullable(bound).orElseThrow(() -> new NullPointerException());
        return random.nextLong(bound) + 1;
    }
}
