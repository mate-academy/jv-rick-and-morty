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

@Tag(name = "Character management", description = "Endpoints for managing products")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Return a character by random id")
    public InternalCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Filter by the given name",
            description = "Returns a list of all characters whose name contains the search string")
    public List<InternalCharacterDto> getCharactersByName(@RequestParam String partialName) {
        return characterService.getCharactersByName(partialName);
    }
}
