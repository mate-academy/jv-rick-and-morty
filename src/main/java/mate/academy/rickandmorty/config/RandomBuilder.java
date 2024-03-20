package mate.academy.rickandmorty.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RandomBuilder {
    @Bean
    public Random getRandom() {
        return new Random();
    }
}
