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
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character management", description = "Endpoints for managing characters")
public class CharactersController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character", description = "Get random character")
    @GetMapping("/random")
    private CharacterDto getAnyCharacter() {
        return characterService.findAnyCharacter();
    }

    @Operation(summary = "Get characters by name",
            description = "Get a list of characters that have the specified row in their name ")
    @GetMapping("/by-name")
    private List<CharacterDto> findByName(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
