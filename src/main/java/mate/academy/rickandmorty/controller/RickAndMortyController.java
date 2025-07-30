package mate.academy.rickandmorty.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rickandmorty")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty API", description = "Character browser")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    @GetMapping("/random")
    @Operation(summary = "Get random character")
    public Character getRandomCharacter() {
        return rickAndMortyService.randomCharacterGenerated();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get character by name")
    public List<Character> getByName(@PathVariable String name) {
        return rickAndMortyService.findByName(name);
    }
}
