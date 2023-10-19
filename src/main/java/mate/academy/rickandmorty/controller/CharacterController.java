package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty character management",
        description = "endpoints for working with third-party Rick and Morty API"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rick-and-morty/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(
            summary = "Retrieve random character",
            description = "Retrieve random character from database."
    )
    @GetMapping("/random")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @Operation(
            summary = "Search characters by name",
            description = "Search all characters whose name contains the search string."
    )
    @GetMapping("/search")
    public List<CharacterDto> searchByName(
            @RequestParam String name, @PageableDefault(sort = "id", size = 5) Pageable pageable) {
        return characterService.searchCharacters(name, pageable);
    }
}
