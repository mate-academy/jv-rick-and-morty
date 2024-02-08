package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharactersDto;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick And Morty", description = "API for working Rick And Morty")
@RequiredArgsConstructor
@RequestMapping("/characters")
@RestController
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character", description = "Randomly generates a wiki about "
            + "one character in the universe the animated series Rick & Morty.")
    @GetMapping("/randon")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search string", description = "The request takes a string as an argument,"
            + " and returns a list of all characters whose name contains the search string")
    @GetMapping("/search/{searchString}")
    public CharactersDto getAllCharactersBySearchString(@PathVariable String searchString) {
        return characterService.getCharactersBySearchString(searchString);
    }
}
