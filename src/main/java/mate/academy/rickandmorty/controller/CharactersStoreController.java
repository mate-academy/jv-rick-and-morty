package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharactersDto;
import mate.academy.rickandmorty.service.InternalCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CharactersStoreController {
    private final InternalCharacterService internalCharacterService;

    @Operation(summary = "Search character by name field",
            description = "The request takes a string as an argument, "
                    + "and returns a list of all characters whose name contains the search string")
    @GetMapping("/search")
    public List<CharactersDto> getCharactersByName(String name) {
        return internalCharacterService.getCharactersByName(name);
    }

    @Operation(summary = "Get random character",
            description = "Randomly generates a wiki about one character in the universe"
                    + " the animated series Rick & Morty")
    @GetMapping("/random")
    public CharactersDto getRandomCharacter() {

        return internalCharacterService.getRandomCharacter();
    }
}
