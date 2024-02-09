package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters", description = "Operations related to Rick an Morty characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/characters")
public class RickAndMortyController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character")
    @GetMapping
    public CharacterInternalDto getWikiAboutRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search a character")
    @GetMapping("/by-name")
    public List<CharacterInternalDto> getCharactersByName(@RequestParam String name) {
        return characterService.findCharacterByName(name);
    }
}
