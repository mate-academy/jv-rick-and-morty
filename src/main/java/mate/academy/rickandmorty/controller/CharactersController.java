package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mate.academy.rickandmorty.dto.CharactersDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters", description = "Character API")
@RestController
@RequestMapping("/api/character")
public class CharactersController {
    private final CharacterService characterService;

    public CharactersController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Operation(summary = "Get random character",
            description = "Returns a random character from the database.")
    @GetMapping("/random")
    public CharactersDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search characters by name",
            description = "Returns a list of characters matching the search query.")
    @GetMapping("/search")
    public Page<CharactersDto> searchCharacters(@RequestParam(defaultValue = "") String name,
                                                Pageable pageable) {
        return characterService.searchByName(name, pageable);
    }
}
