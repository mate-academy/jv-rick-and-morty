package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {
    private final CharacterService charactersService;

    @Operation(summary = "Getting a random character",
            description = "Getting a random character from the Rick & Morty cartoon universe")
    @GetMapping("/random-character")
    public CharacterDto getRandomCharacter() {
        return charactersService.getRandomCharacter();
    }

    @Operation(summary = "Search characters by name",
            description = "Search a list of all characters whose name contains the search string")
    @GetMapping("/name")
    public List<CharacterDto> searchByName(String name) {
        return charactersService.getCharactersWhoseNameContains(name);
    }
}
