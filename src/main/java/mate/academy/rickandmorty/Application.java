package mate.academy.rickandmorty;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class Application {
    private final CharacterClient client;

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> client.loadCharactersData();
    }
}
