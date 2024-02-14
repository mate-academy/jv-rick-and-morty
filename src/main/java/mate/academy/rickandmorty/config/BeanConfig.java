package mate.academy.rickandmorty.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public Random randomBean() {
        return new Random();
    }
}
