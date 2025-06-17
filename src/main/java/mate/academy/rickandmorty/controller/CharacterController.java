package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick And Morty",
        description = "Endpoints for getting information about RnM characters")
@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Endpoint for getting a random character from DB")
    public RnMCharacter getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Get characters by name",
            description = "Get a list of characters that contains the name")
    public List<RnMCharacter> getCharactersContainingName(@RequestParam String name) {
        return characterService.getCharactersContainingName(name);
    }
}
