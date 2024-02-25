package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/{name}")
    @Operation(summary = "Get the character by name", description = "Get the character by name")
    public List<CharacterDto> getCharactersByName(@PathVariable String name) {
        return characterService.findCharacterByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get character",
            description = "Get a character randomly")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }
}
