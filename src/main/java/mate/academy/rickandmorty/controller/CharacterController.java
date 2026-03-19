package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character", description = "Endpoints for managing characters")
@RestController
@RequestMapping("/characters")
@AllArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(
            summary = "Get a random character",
            description = "Returns a random character from the database"
    )
    @GetMapping("/random/character")
    public CharacterInternalDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(
            summary = "Find characters by name",
            description = "Returns a list of characters that contain the given name"
    )
    @GetMapping("/find")
    public List<CharacterInternalDto> findByContainsName(@RequestParam String name) {
        return characterService.getCharactersContainsName(name);
    }
}
