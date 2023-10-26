package mate.academy.rickandmorty;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.client.RickAndMortyClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Tag(
        name = "Application class",
        description = "Main application class for managing Rick and Morty character data")
@SpringBootApplication
public class Application {
    private static CharacterService characterService;
    private static RickAndMortyClientApi rickAndMortyClient;

    @Autowired
    public Application(CharacterService service, RickAndMortyClientApi client) {
        Application.rickAndMortyClient = client;
        Application.characterService = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        List<CharacterResultDto> result = rickAndMortyClient.getCharacters();
        characterService.saveAll(result);
    }
}
