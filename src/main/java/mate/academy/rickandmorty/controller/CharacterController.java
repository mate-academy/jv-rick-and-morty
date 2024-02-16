package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParam;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/characters")
@Tag(name = "Character API", description = "API endpoints for managing characters.")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a Random CharacterEntity",
            description = "Retrieve a random characterEntity from the database.")
    public CharacterEntity getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Search Characters", description = "Search for characters by name.")
    public List<CharacterEntity> searchCharacter(CharacterSearchParam name) {
        return characterService.search(name);
    }
}
