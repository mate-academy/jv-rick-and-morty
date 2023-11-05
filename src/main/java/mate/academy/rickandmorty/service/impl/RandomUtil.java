package mate.academy.rickandmorty.service.impl;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomUtil {
    @Bean
    Random util() {
        return new Random();
    }
}
