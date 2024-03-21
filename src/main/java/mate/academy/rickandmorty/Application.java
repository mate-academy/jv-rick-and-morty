package mate.academy.rickandmorty;

import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class Application implements CommandLineRunner {
    private CharacterService characterService;
    private CharacterClient characterClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        characterService.saveAll(characterClient.getAllCharacters().getResults());
    }
}
