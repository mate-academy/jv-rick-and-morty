package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get random characters",
            description = "Get a list of all random characters")
    public RickAndMortyCharacter getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/find_name")
    @Operation(summary = "Get characters by name",
            description = "Finds a name spelled in full or in part")
    public List<RickAndMortyCharacter> getAllByNameContain(@RequestParam String name) {
        return characterService.findByNameContaining(name);
    }
}
