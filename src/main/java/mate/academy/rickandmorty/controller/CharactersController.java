package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management",
        description = "API for getting wiki about characters from Rick and Morty")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharactersController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character wiki",
            description = "Returns a random character wiki")
    public CharacterInternalDto getRandomCharacterWiki() {
        return characterService.getRandomCharacterWiki();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get characters by name",
            description = "Returns a list of all characters whose name contains the search string")
    public List<CharacterInternalDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
