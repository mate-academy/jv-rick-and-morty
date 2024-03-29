package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Mort API",
        description = "Endpoints for random character or finding a "
                + "character that contains specific name")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(
            summary = "Generate random character",
            description = "Generates a wiki of a random character from Rick and Morty universe")
    public CartoonCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search by name",
            description = "Returns list of characters containing the search name")
    public List<CartoonCharacterDto> searchByName(@RequestParam String name) {
        return characterService.searchByName(name);
    }
}
