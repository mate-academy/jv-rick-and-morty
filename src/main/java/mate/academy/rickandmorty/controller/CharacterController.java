package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
@Tag(name = "Characters", description = "Endpoints for Rick and Morty characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Returns a random character from the internal database")
    public CharacterDto getRandom() {
        return characterService.getRandom();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name",
            description = "Returns a list of characters whose name contains the search string")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a character")
    public List<CharacterDto> search(
            @Parameter(description =
                    "The name (or part of the name) of the character to search for")
            @RequestParam String name) {
        return characterService.searchByName(name);
    }
}
