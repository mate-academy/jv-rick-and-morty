package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character selector", description = "Endpoint for viewing characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(
            summary = "Get a random character",
            description = "Get a random character"
    )
    public CharacterDto getRandomCharacter() {
        return characterService.findRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search characters by name",
            description = "Search characters by name containing ignore case"
    )
    public List<CharacterDto> getCharactersByName(
            @RequestParam String name,
            Pageable pageable
    ) {
        return characterService.findCharactersByName(name, pageable);
    }
}
