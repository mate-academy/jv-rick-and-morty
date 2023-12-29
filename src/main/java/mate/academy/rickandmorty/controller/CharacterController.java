package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Character info API",
        description = "Endpoints for getting character info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get a character by id", description = "Get a character by id")
    @GetMapping("/{id}")
    public CharacterDto getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @Operation(summary = "Get characters by name pattern",
            description = "Get a list of characters with a name matching pattern")
    @GetMapping
    public List<CharacterDto> getPatternNameCharacters(@RequestBody String pattern) {
        return characterService.getPatternNameCharacters(pattern);
    }
}
