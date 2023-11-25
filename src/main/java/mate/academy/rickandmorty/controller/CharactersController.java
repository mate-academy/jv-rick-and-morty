package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.InternalCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CharactersController {
    private final InternalCharacterService internalCharacterService;

    @Operation(summary = "Search character by name",
            description = "Search for all characters whose name contains the search string")
    @GetMapping("/search")
    public List<CharacterDto> getCharactersByName(String name) {
        return internalCharacterService.getCharactersByName(name);
    }

    @Operation(summary = "Get random character",
            description = "Randomly generates a wiki about one character")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {

        return internalCharacterService.getRandomCharacter();
    }
}
