package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.dtos.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters", description = "Operations related to characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character")
    @GetMapping("/random")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Find a character", description = "Search for a character by name")
    @GetMapping("/by-name")
    List<CharacterDto> findByName(@PathVariable String namePart) {
        return characterService.findAllByPartName(namePart);
    }
}
