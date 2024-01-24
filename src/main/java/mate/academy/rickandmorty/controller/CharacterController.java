package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RestController
@RequestMapping("/wiki")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get all characters by name(paged)",
            description = "Returns a list of characters by name")
    public Page<CharacterDto> getAllByName(@RequestParam String name, Pageable pageable) {
        return characterService.getAllByName(name, pageable);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Generates a random character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

}
