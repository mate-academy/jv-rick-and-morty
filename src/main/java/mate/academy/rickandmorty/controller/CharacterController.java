package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get Random Character", description = "Get random character")
    @GetMapping
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get Characters By name",
            description = "Get list of characters, that contains name ignoring the case")
    @GetMapping("/{name}")
    public List<CharacterDto> getByName(@PathVariable String name) {
        return characterService.getByName(name);
    }

}
