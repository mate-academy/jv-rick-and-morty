package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.service.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Characters",
        description = "Operations related to Rick and Morty characters"
)
@RestController
@RequestMapping("/rickAndMorty")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @Operation(
            summary = "Get random character",
            description = "Returns a random character from the database"
    )
    @GetMapping("/random")
    public ResponseCharacterDto getRandomCharacter() {
        return appService.getRandomCharacter();
    }

    @Operation(
            summary = "Search characters by name",
            description =
                    "Returns characters whose name contains the given value (case-insensitive)"
    )
    @GetMapping("/search")
    public List<ResponseCharacterDto> searchCharactersByNameContaining(
            @Parameter(
                    description = "Part of character name",
                    example = "rick",
                    required = true
            )
            @RequestParam String name) {
        return appService.searchCharactersByNameContaining(name);
    }

}
