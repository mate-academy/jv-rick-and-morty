package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "API Endpoints", description = "Endpoints for retrieving characters from the wiki")
@RequiredArgsConstructor
public class ApiController {
    private final CharacterService characterService;

    @GetMapping("/generator")
    @Operation(summary = "Get a random character",
            description = "Get a wiki about random character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Get characters that contain name", description =
            "Get a list of characters whose name contains the specified name")
    public List<CharacterDto> getCharactersContainName(@RequestParam(defaultValue = "")
                                                           String name) {
        return characterService.getCharactersContainName(name);
    }
}
