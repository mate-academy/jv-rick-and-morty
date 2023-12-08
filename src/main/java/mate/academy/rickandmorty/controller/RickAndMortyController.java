package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rickandmorty")
public class RickAndMortyController {
    private final CharacterService characterService;

    @GetMapping("/randomwiki")
    @Operation(summary = "Get one random wiki",
            description = "Get one random wiki about characters")
    public SeriesCharacterResponseDto getRandomWiki() {
        return characterService.getRandomWiki();
    }

    @GetMapping("/findbyname")
    @Operation(summary = "find information about character by name",
            description = "return character array where character name contains param name"
                    + "support pagination")
    @Parameter(name = "name", description = "name contains the search string")
    @Parameter(name = "page", description = "open specified page, default value = 0",
            required = true, example = "0")
    @Parameter(name = "size", description = "describes count element per page",
            required = true, example = "10")
    public List<SeriesCharacterResponseDto> findCharactersByName(
            Pageable pageable,
            @RequestParam String name) {
        return characterService.findCharactersByName(name, pageable);
    }
}
