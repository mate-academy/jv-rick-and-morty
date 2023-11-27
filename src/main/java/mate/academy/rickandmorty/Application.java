package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            RickAndMortyClient rickAndMortyClient,
            CharacterServiceImpl characterService
    ) {
        return args -> {
            String characters = rickAndMortyClient.makeRequest();
            List<CharacterEntity> parsedData = rickAndMortyClient.parseCharactersJson(characters);
            characterService.saveAll(parsedData);
        };
    }
}
