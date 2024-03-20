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

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class RickAndMortyController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Returns a random character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get characters by name",
            description = "Returns a list of all characters with the given name")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
