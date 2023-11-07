package mate.academy.rickandmorty.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RandomConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}
