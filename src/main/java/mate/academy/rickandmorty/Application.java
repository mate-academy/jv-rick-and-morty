package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final RickAndMortyClient rickAndMortyClient;
    private final RickAndMortyService rickAndMortyService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                rickAndMortyClient.getCharacters(CHARACTER_URL)
                        .forEach(t -> t.getRickAndMortyDataResponseDtoList()
                                .forEach(rickAndMortyService::save));
            }
        };
    }
}
