package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.RickAndMortyClient;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static RickAndMortyClient rickAndMortyClient;
    private static RickAndMortyService rickAndMortyService;

    public Application(RickAndMortyClient client, RickAndMortyService rickAndMortyService) {
        Application.rickAndMortyClient = client;
        Application.rickAndMortyService = rickAndMortyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        rickAndMortyService.saveAll(rickAndMortyClient.getAllCharacter());
    }
}
