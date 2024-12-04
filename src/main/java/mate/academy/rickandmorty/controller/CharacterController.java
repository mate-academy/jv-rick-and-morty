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

@Tag(name = "Character Management",
        description = "Endpoints for managing Rick and Morty characters")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random-wiki")
    @Operation(summary = "Get a random character",
            description = "Retrieve a random character from the Rick and Morty universe")
    public CharacterEntity getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by part of name",
            description = "Search for characters whose name contains the specified parameter")
    public List<CharacterEntity> search(@RequestParam String name) {
        return characterService.search(name);
    }
}
