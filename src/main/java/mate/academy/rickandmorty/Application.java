package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static CharacterService characterService;

    @Autowired
    public Application(CharacterService characterService) {
        Application.characterService = characterService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        characterService.saveAllToDB();
    }
}
