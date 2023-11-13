package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RickAndMorty", description =
        "This application looking for characters by name or random")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rickAndMorty")
public class RickAndMortyController {
    private final RickAndMortyService rickAndMortyService;

    @Operation(summary = "Find random character")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @Operation(summary = "Find characters by name")
    @GetMapping("/search/{name}")
    public List<CharacterDto> findByName(@PathVariable String name) {
        return rickAndMortyService.findByName(name);
    }
}
