package mate.academy.rickandmorty;

import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {
    private static CharacterService characterService;
    private static RickAndMortyClient rickAndMortyClient;

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
