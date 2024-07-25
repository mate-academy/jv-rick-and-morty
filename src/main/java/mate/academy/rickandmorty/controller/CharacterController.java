package mate.academy.rickandmorty.controller;

import java.util.List;
import java.util.Random;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterClient characterClient;

    public CharacterController(CharacterClient characterClient) {
        this.characterClient = characterClient;
    }

    @GetMapping
    public CharacterResponseDto getRandomCharacter() {
        Random random = new Random();
        int randomInt = random.nextInt(1, 827);
        String randomString = Integer.toString(randomInt);
        return characterClient.getCharacters(randomString);
    }

    @GetMapping("/{name}")
    public List<CharacterResponseDto> getCharactersByName(@PathVariable String name) {
        return characterClient.getCharactersByName(name);
    }
}
