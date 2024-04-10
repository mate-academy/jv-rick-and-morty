package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.ApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static ApiService apiService;

    public Application(ApiService apiService) {
        Application.apiService = apiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        apiService.fetch();
    }
}
