package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ResponseCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character management", description = "Endpoints for managing characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Get a random character from DB")
    public ResponseCharacterDto getRandom() {
        return characterService.getRandom();
    }

    @GetMapping("/{template}")
    @Operation(summary = "Search characters by template",
            description = "Get a list of characters which name contains following template")
    public List<ResponseCharacterDto> searchByTemplate(@PathVariable String template) {
        return characterService.searchByTemplate(template);
    }
}
