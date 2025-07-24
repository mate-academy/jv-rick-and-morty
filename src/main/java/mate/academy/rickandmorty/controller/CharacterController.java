package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.services.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(description = "Returns a random Rick and Morty character")
    public CharacterDto getRandom() {
        return characterService.findRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(description = "Returns a list of characters that match the given name")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
