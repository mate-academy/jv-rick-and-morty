package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Returns a randomly selected character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name",
            description = "Returns all characters whose name contains the search string")
    public List<CharacterDto> searchByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
