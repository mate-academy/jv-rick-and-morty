package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static RickAndMortyClient rickAndMortyClient;

    public Application(RickAndMortyClient client) {
        Application.rickAndMortyClient = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        rickAndMortyClient.getCharacters();
    }
}
