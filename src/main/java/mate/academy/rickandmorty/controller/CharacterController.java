package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.services.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/random")
    @Operation(summary = "Create random character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Character was not found in DB")
    })
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Find all characters with specific string in their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characters found successfully"),
            @ApiResponse(responseCode = "404", description = "Name was not found in DB")
    })
    public List<Character> getCharacterList(@RequestParam(defaultValue = "ick") String str) {
        return characterService.findByString(str);
    }
}
