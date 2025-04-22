package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private final CharacterService service;

    public Application(CharacterService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runDatabaseInitialization() {
        return args -> service.initializeDatabase();
    }
}
