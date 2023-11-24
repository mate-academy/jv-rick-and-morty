package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.impl.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/character")
@Tag(name = "Rick and Morty API", description = "API endpoints for movie characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @Operation(summary = "Get random movie character",
            description = "Get a random movie character",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            })
    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get movie characters by name",
            description = "Get a list of movie characters by name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            },
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the movie character",
                            example = "Cynthia"
                    )
            })
    @GetMapping("/{name}")
    public List<CharacterResponseDto> getByName(@RequestParam String name) {
        return characterService.getByName(name);
    }
}
