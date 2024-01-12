package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterRequestDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty info bureau",
        description = "Provides information about Rick and Morty universe")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get random character",
            description = "The request randomly generates a wiki about one "
                    + "character in the universe of the animated series Rick & Morty")
    public CharacterRequestDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Search by name",
            description = "The request takes a string as an argument, "
                    + "and returns a list of all characters whose "
                    + "name contains the search string")
    public List<CharacterRequestDto> getCharactersByName(@PathVariable String name) {
        return characterService.findByName(name);
    }
}
