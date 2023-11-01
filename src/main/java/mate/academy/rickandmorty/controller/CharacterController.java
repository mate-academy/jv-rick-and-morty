package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick&Morty API",
        description = "Endpoints for accepting characters from the Rick&Morty universe")
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random character", description = "Allows get one random character")
    @GetMapping("/random-character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search character by name or part name",
            description = "Allows to find character by name or part name")
    @GetMapping("/search/name={partName}")
    public List<CharacterDto> findCharacterByNameContains(@PathVariable String partName) {
        return characterService.findCharacterByNameContains(partName);
    }
}
