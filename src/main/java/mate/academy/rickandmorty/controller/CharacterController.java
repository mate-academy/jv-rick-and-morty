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

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
@Tag(name = "Characters", description = "Operations related to retrieving characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(
            summary = "Get all characters",
            description = "Get the list of all characters"
    )
    public List<CharacterDto> getAll() {
        return characterService.getAll();
    }

    @GetMapping(params = "name")
    @Operation(
            summary = "Search characters by name",
            description = "Search characters using filter by name ignoring case"
    )
    public List<CharacterDto> getByName(@RequestParam String name) {
        return characterService.getByName(name);
    }
}
