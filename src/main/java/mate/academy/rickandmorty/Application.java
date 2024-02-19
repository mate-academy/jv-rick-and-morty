package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final CharacterService characterService;

    @Autowired
    public Application(CharacterService characterService) {
        this.characterService = characterService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        characterService.fillDataBase();
    }
}
