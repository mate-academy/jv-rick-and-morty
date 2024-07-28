package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.OutputCharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management",
        description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService animationService;

    @Operation(
            summary = "Retrieve character",
            description = "Get a character with random id from "
                    + "rick & morty"
    )
    @GetMapping
    public OutputCharacterResponseDto getCharacter() {
        return animationService.findCharacterByRandomId();
    }

    @Operation(
            summary = "searching characters",
            description = "get a character with your parameters"
                    + "u can search by letter or full name"
    )
    @GetMapping("/search")
    public List<OutputCharacterResponseDto> getWithLettersInName(
            @RequestParam String name) {
        return animationService.findCharactersByName(name);
    }
}
