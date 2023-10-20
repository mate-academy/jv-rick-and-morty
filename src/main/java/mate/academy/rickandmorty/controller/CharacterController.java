package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Character management", description = "Endpoints for getting characters from db")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character",
            description = "Get a random characters from Rick and Morty world")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name's part",
            description = "Search characters by name's part")
    public List<CharacterDto> searchCharacter(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
