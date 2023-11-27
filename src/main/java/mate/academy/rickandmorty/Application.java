package mate.academy.rickandmorty;

import mate.academy.rickandmorty.controller.ApiController;
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
            ApiController apiController = new ApiController(characterService);

            rickAndMortyClient.makeRequest();
        };
    }
}
