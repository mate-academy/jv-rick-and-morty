package mate.academy.rickandmorty.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rickAndMortyOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rick & Morty API")
                        .description("Rick & Morty REST API documentation")
                        .version("1.0.0"));
    }
}
