package mate.academy.rickandmorty;

import mate.academy.rickandmorty.client.SeriesCharacterClient;
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
    public CommandLineRunner commandLineRunner(SeriesCharacterClient seriesCharacterClient) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                seriesCharacterClient.loadSeriesCharacters();
            }
        };
    }
}
