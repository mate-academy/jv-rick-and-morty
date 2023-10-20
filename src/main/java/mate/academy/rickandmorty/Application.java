package mate.academy.rickandmorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    //private static final CharacterService characterService =
    //        new CharacterServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //characterService.saveAll();
    }
}
