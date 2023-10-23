package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class Application {
    private final CharacterClient characterClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    void initDataBase() {
        characterClient.getCharacterMetaInfo();
    }
}
