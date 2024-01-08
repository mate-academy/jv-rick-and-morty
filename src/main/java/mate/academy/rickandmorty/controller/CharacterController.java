package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.dto.local.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
@Tag(name = "Rick and Morty Characters Controller",
        description = "Endpoints for accessing characters from Rick and Morty series")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Retrieve a Random Character",
            description = "Retrieves a randomly selected character from the series")
    CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/")
    @Operation(summary = "Search Characters by Name",
            description = "Retrieves a list of characters whose names"
                    + " contain the provided search string")
    List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
