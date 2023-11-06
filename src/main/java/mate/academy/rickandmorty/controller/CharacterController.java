package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.internal.Character;
import mate.academy.rickandmorty.service.CharacterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing Characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CharacterController {
    private final CharacterServiceImpl characterService;

    @GetMapping("/character/random")
    @Operation(summary = "Get random character", description = "Get random character")
    public String getRandomCharacter() {
        Character randomCharacter = characterService.getRandomCharacter();
        return randomCharacter.toString();
    }

    @GetMapping("/search")
    @Operation(summary = "Get all character which contain arguments",
            description = "Get all character which contain arguments")
    public String searchCharactersByName(@RequestParam String nameContains) {
        List<Character> characters = characterService.searchCharactersByName(nameContains);
        return characters.toString();
    }
}
