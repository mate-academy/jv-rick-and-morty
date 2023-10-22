package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static RickAndMortyClient rickAndMortyClient;
    private static CharacterService characterService;

    @Autowired
    public Application(CharacterService service, RickAndMortyClient client) {
        Application.rickAndMortyClient = client;
        Application.characterService = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        List<CharacterResponseDto> characters = rickAndMortyClient.getCharacters();
        characterService.saveAll(characters);
    }
}
