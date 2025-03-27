package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters management", description = "Endpoints for managing "
        + "Characters from Rick and Morty franchise")
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class RickMortyController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random wiki",
            description = "Get wiki of a random hero from Rick and Morty")
    CharacterResponseDto generateWiki() {
        return characterService.getRandomWiki();
    }

    @GetMapping
    @Operation(summary = "Get all Characters with name contains",
            description = "Get all Characters with name contains given string in request param")
    List<CharacterResponseDto> getAllCharactersNameLike(@RequestParam String name) {
        return characterService.getCharactersWithNameLike(name);
    }
}
