package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for getting characters from DB")
@RequiredArgsConstructor
@RestController
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character",
            description = "Get a random characters from Rick and Morty world")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search characters",
            description = "Search characters by name")
    @GetMapping("/search")
    public List<CharacterDto> searchCharacter(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
