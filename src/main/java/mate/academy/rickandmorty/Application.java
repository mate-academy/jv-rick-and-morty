package mate.academy.rickandmorty;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import mate.academy.rickandmorty.service.CartoonCharacterClient;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    @RequiredArgsConstructor
    public class DataLoader implements ApplicationRunner {
        private final CartoonCharacterClient characterClient;
        private final CartoonCharacterService characterService;

        public void run(ApplicationArguments args) {
            List<CreateCartoonCharacterRequestDto> characters = characterClient.getAllCharacters();
            characterService.saveAll(characters);
        }
    }
}
