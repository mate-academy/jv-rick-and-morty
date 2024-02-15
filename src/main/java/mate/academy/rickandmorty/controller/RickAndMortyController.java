package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Rick & Morty Characters Information Service",
        description = "Service for retrieving information about "
                + "characters in the Rick & Morty universe")
@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final RickAndMortyServiceImpl rickAndMortyService;

    @GetMapping("/random")
    @Operation(
            summary = "Get Random Character",
            description = "Get information about a randomly selected character."
    )
    public RickAndMortyCharacterDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @GetMapping("/name-contains")
    @Operation(
            summary = "Find Characters by Name",
            description = "Get a list of characters whose name "
                    + "contains the specified segment (case-insensitive)."
    )
    public List<RickAndMortyCharacterDto> findCharactersByNameContaining(
            @RequestParam String nameSegment) {
        return rickAndMortyService.findCharactersByNameContaining(nameSegment);
    }
}
