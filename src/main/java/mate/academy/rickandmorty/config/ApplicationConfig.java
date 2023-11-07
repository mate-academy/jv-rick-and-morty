package mate.academy.rickandmorty.config;

import java.net.http.HttpClient;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
