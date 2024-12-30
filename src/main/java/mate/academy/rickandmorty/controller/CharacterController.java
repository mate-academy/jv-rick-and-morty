package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character Controller",
        description = "Endpoints for managing characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Get random character from the database")
    public CharacterEntity getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name",
            description = "Search characters by name in the database")
    public List<CharacterEntity> searchCharactersByName(@RequestParam String name) {
        return characterService.search(name);
    }

    @GetMapping
    @Operation(summary = "Get all characters",
            description = "Get all characters from the database")
    public List<CharacterEntity> getCharacters() {
        return characterService.getCharacters();
    }
}
