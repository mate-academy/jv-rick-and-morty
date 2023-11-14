package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management",
        description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character",
            description = "Get some random character")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search character by name",
            description = "Find some characters by name")
    @GetMapping("/search")
    public List<CharacterDto> searchCharacter(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
