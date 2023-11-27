package mate.academy.rickandmorty;

import java.io.IOException;
import mate.academy.rickandmorty.controller.ApiController;
import mate.academy.rickandmorty.service.ApiService;
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
            ApiService apiService,
            CharacterServiceImpl characterService
    ) {
        return args -> {
            ApiController apiController = new ApiController(apiService, characterService);

            try {
                apiController.fetchData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
