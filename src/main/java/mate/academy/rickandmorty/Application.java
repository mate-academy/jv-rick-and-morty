package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private final CharacterClient characterClient;

    @Autowired
    public Application(CharacterClient characterClient) {
        this.characterClient = characterClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                characterClient.getAllCharacters();
            }
        };
    }
}
