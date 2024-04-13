package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wiki")
@Tag(name = "Character management", description = "Endpoints for getting characters")
public class RickAndMortyController {
    private final RickAndMortyService rickAndMortyService;

    @GetMapping
    @Operation(summary = "Get a random character", description = "Get a random character")
    public CharacterResponseDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @GetMapping("/params")
    @Operation(summary = "Get all characters contain putting string into parameter name",
            description = "Get all characters contain putting string into parameter name")
    public List<CharacterResponseDto> getByName(@RequestParam(name = "name") String name) {
        return rickAndMortyService.findByNameLike(name);
    }
}
