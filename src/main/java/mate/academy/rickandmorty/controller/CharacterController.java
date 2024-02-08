package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.ApiDataServiceImpl;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick And Morty", description = "API for working Rick And Morty")
@RequiredArgsConstructor
@RequestMapping("/characters")
@RestController
public class CharacterController {
    private final ApiDataServiceImpl apiDataServiceImpl;
    private final CharacterService characterService;

    @Operation(summary = "Get random character", description = "Randomly generates a wiki about "
            + "one character in the universe the animated series Rick & Morty.")
    @GetMapping("/randon")
    public Character getRandomCharacter() {
        apiDataServiceImpl.getDataFromApi();
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search string", description = "The request takes a string as an argument,"
            + " and returns a list of all characters whose name contains the search string")
    @GetMapping("/search/{searchString}")
    public List<Character> testSearch(@PathVariable String searchString) {
        apiDataServiceImpl.getDataFromApi();
        return characterService.getCharactersBySearchString(searchString);
    }
}
