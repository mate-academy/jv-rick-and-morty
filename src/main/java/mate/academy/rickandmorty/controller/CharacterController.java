package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rickandmortyapi")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @Operation(summary = "Get random character",
            description = "Returns a random character")
    @GetMapping("/random")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get a character by name",
            description = "Returns a character searched by name")
    @GetMapping("/search")
    public List<Character> getCharacterById(String name) {
        return characterService.searchCharacterByName(name);
    }

}
