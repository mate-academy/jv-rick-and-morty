package mate.academy.rickandmorty.service.random.impl;

import java.util.Random;
import mate.academy.rickandmorty.service.random.RandomService;
import org.springframework.stereotype.Component;

@Component
public class RandomServiceImpl implements RandomService {
    private final Random random = new Random();

    @Override
    public int getRandomIntNumber(int from, int to) {
        return random.nextInt((to - from) + 1) + from;
    }
}
