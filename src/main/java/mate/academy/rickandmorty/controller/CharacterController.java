package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character API", description = "Look at and search for characters")
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    @Operation(
            summary = "Get random character",
            description = "The request randomly generates a wiki about one"
                          + " character in the universe the animated series Rick & Morty"
    )
    @GetMapping("/random")
    public CharacterResponseDto getRandom() {
        return null;
    }

    @Operation(
            summary = "Search character",
            description = "Search for characters based on the name"
    )
    @GetMapping("/search")
    public List<CharacterResponseDto> search(@PathVariable String name) {
        return List.of();
    }


}
