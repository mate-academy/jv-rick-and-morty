package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.impl.RickAndMortyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<Character> fillDataBase(CharacterService characterService,
                                        RickAndMortyClient client) {
        return characterService.saveAllCharacters(client.getCharacters());
    }
}
