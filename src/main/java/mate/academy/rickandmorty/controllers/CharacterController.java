package mate.academy.rickandmorty.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.models.Character;
import mate.academy.rickandmorty.service.RickMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "Endpoints for API")
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {
    @Autowired
    private RickMortyService rickMortyService;

    @Operation(summary = "Find all characters by name", description = "Get all characters by name")
    @GetMapping("/by-name")
    public List<Character> getAllByName(@RequestParam("name") String name) {
        return rickMortyService.getAllCharactersByName(name);
    }

    @Operation(summary = "Find random character", description = "Get random character")
    @GetMapping("/random-character")
    public Character getRandomCharacter() {
        return rickMortyService.getRandomCharacter();
    }
}
