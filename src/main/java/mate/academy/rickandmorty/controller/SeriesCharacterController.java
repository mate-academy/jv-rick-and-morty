package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterWikiDto;
import mate.academy.rickandmorty.service.SeriesCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/character-wiki")
@Tag(
        name = "Rick and Morty Character Wiki API",
        description = "Endpoints for getting character wiki"
)
public class SeriesCharacterController {
    private final SeriesCharacterService characterService;

    @GetMapping
    @Operation(
            summary = "Get a random character wiki",
            description = "Returns a random character wiki"
    )
    public SeriesCharacterWikiDto getRandomCharacter() {
        return characterService.getRandomSeriesCharacter();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search characters by name",
            description = "Returns a list of characters whose names contain a string"
    )
    public List<SeriesCharacterWikiDto> getCharactersNameContains(
            @RequestParam("name") String name
    ) {
        return characterService.findNameContains(name);
    }
}
