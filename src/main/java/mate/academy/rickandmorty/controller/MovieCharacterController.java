package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
@Tag(name = "'Rick and Morty' controller",
        description = "Finds random 'Rick and Morty' character "
            + "and finds all characters containing the defined string part")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random 'Rick and Morty' character",
            description = "The random ID value is generated "
                    + "in the range of characters from the database")
    public MovieCharacterResponseDto getRandom() {
        return movieCharacterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get all 'Rick and Morty' characters whose names contain requested part",
            description = "Get all 'Rick and Morty' characters whose names contain requested part")
    public List<MovieCharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return movieCharacterService.findAllByNameContains(namePart);
    }
}
