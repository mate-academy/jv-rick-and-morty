package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(
            summary = "Get random character",
            description = "Get character from database by random index")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.findRandomCharacter();
    }

    @Operation(
            summary = "Get character by name",
            description = "Get list of characters from database using filter by name")
    @GetMapping("/by-name")
    public List<CharacterDto> getCharacterByName(@RequestParam String name) {
        return characterService.findCharactersByName(name);
    }
}
