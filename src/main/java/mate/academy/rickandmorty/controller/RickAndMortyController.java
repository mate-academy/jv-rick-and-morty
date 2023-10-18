package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import mate.academy.rickandmorty.service.character.RickAndMortyCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Actions with characters",
        description = "Endpoints which indicates on a specific action with Character")
@RequiredArgsConstructor
@RestController
@RequestMapping("/rickandmorty")
public class RickAndMortyController {
    private final RickAndMortyCharacterService service;

    @GetMapping("/random")
    @Operation(summary = "Get random Character",
            description = "Get random Character which is stored in internal DB")
    public RickAndMortyCharacter getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @Operation(summary = "Get Characters by search param(name)",
            description = "Receive all Characters containing name")
    @GetMapping("/find")
    public List<RickAndMortyCharacter> getByNameContaining(@RequestParam String name) {
        return service.findByNameContaining(name);
    }
}
