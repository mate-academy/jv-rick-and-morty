package mate.academy.rickandmorty.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomBeanConfig {
    
    @Bean
    public Random random() {
        return new Random();
    }
}
