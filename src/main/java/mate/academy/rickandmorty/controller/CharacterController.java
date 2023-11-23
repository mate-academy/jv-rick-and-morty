package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Rick and morty characters management", description = "Endpoints for getting characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get one random character",
            description = "Endpoint for getting random character of Rick and Morty")
    @GetMapping("/random")
    public Character getRandomCharacterDto() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get all characters by param",
            description = "Endpoint for getting "
                    + "all characters of Rick and Morty by input param")
    @GetMapping("/by-input")
    public List<Character> getAllCharactersByParam(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
