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

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Characters", description = "Operations related to characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(
            summary = "Get random character",
            description = "Returns a random character form database",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Character returned successfully")
            }
    )
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @GetMapping()
    @Operation(
            summary = "Get list of character by name-part",
            description = "Returns a list of characters by containing in name next input",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "List returned successfully")
            }
    )
    public List<CharacterDto> getByName(@RequestParam String name) {
        return characterService.getByName(name);
    }
}
