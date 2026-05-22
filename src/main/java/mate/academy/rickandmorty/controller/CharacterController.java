package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management",
                description = "Endpoints for managing Rick and Morty characters")
@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character", 
                    description = "Returns a randomly selected character from the downloaded DB")
    public InternalCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name",
                description = "Returns a list of characters matching the provided name substring")
    public List<InternalCharacterDto> searchCharacterByName(@RequestParam("name") String name) {
        return characterService.findAllByNameContainingIgnoreCase(name);
    }
}
