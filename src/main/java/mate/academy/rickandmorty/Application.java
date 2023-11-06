package mate.academy.rickandmorty;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
            title = "Rick and Morty character service",
            version = "0.1"
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
