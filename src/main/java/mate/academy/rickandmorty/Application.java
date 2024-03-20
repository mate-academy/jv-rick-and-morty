package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    @Autowired
    private CharacterService characterService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> characterService.saveCharactersToDb(BASE_URL);
    }
}
