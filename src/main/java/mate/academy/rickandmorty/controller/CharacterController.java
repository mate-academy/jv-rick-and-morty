package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/init")
    @Operation(summary = "Init characters",
            description = "Fetch all characters from api database and add to local database")
    public void initAllCharacters() {
        characterService.initCharacters();
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Get a random character from api db")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Get all characters by name",
            description = "Get a list of all characters by name parameter")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
