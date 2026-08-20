package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConditionalOnProperty(
            name = "character.import.enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    CommandLineRunner run(CharacterImportService service) {
        return args -> service.fetchAndSaveAllCharacters();
    }
}
