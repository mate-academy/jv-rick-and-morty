package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.initialization.ThirdPartyApiDataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final ThirdPartyApiDataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
