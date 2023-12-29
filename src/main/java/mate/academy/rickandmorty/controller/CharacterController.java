package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character info API",
        description = "Endpoints for getting character info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/characters")
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
    public List<CharacterDto> getPatternNameCharacters(@RequestParam String pattern) {
        return characterService.getPatternNameCharacters(pattern);
    }
}
