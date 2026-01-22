package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty")
@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character")
    @GetMapping
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Find character by name")
    @GetMapping("/{name}")
    public List<Character> getCharacterByName(@PathVariable String name) {
        return characterService.getCharacterByName(name);
    }
}
