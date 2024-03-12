package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        CharacterClient charactersClient = context.getBean(CharacterClient.class);
        charactersClient.fetchAndSaveCharacterData();
    }
}
