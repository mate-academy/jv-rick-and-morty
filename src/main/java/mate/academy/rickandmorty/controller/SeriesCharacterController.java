package mate.academy.rickandmorty.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterDto;
import mate.academy.rickandmorty.service.SeriesCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty Characters",
        description = "Endpoints for getting wiki about characters from series Rick and Morty")
@RestController
@RequestMapping("/api/character")
public class SeriesCharacterController {
    private final SeriesCharacterService seriesCharacterService;

    public SeriesCharacterController(SeriesCharacterService seriesCharacterService) {
        this.seriesCharacterService = seriesCharacterService;
    }

    @Operation(summary = "Get wiki about random character",
            description = "Get wiki about random character")
    @GetMapping("/random")
    public SeriesCharacterDto getRandomCharacter() {
        return seriesCharacterService.getRandomCharacter();
    }

    @Operation(summary = "Find characters by name",
            description = "Find characters whose name contains the search string")
    @GetMapping("/find")
    public List<SeriesCharacterDto> findSeriesCharacterByName(@RequestParam String name) {
        return seriesCharacterService.findSeriesCharacterByName(name);
    }
}
