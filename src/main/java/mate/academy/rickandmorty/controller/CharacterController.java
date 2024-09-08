package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random wiki", description = "Get a random wiki about one character "
            + "in the universe the animated series Rick & Morty")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("by-name")
    @Operation(summary = "Get a list of characters with name",
            description = "Get a list of characters"
                    + "whose name contains the search string")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersWithName(name);
    }

}
