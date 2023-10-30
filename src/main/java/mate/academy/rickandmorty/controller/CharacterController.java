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
@RequestMapping("/character")
@Tag(name = "Character management", description = "Endpoint for managing Character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Get random character")
    public ResponseCharacterDto getRandom() {
        return characterService.getRandom();
    }

    @GetMapping("/{names}")
    @Operation(
            summary = "Get characters by names",
            description = "Get list of characters by names"
    )
    public List<ResponseCharacterDto> getAllByString(
            @PathVariable String names
    ) {
        return characterService.getAllByNames(names);
    }
}
