package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.initialization.RickAndMortyApiDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final RickAndMortyApiDataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
