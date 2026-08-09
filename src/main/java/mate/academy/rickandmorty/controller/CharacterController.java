package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoint for interaction with characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @ApiResponse(responseCode = "200", description = "Successfully get character")
    @Operation(summary = "Get random character",
            description = "Get random character")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @ApiResponse(responseCode = "200", description = "Successfully found characters")
    @Operation(summary = "Search characters by name",
            description = "Get list of characters by name")
    @GetMapping("/search")
    public List<CharacterDto> getCharacterByName(@RequestParam String name) {
        return characterService.searchByName(name);
    }
}
