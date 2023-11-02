package mate.academy.rickandmorty;

import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {
    private static CharacterService characterService;
    private static CharacterClient characterClient;
    @Autowired
    public Application(CharacterService service, CharacterClient client) {
        Application.characterClient = client;
        Application.characterService = service;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        List<CharacterResponseDto> characters = characterClient.getCharacters();
        characterService.saveAll(characters);
    }
}
