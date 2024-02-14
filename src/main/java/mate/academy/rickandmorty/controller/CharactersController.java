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

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Characters management",
        description = "Endpoints for managing characters from the Rick and Morty cartoon")
public class CharactersController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character")
    public CharacterInternalDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get all characters by name part")
    public List<CharacterInternalDto> getAllByNamePart(@RequestParam String namePart) {
        return characterService.getAllByNamePart(namePart);
    }

}
