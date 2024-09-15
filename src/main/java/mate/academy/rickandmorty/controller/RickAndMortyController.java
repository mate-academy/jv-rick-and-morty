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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
@Tag(
        name = "Rick and Morty controller",
        description = "endpoints for getting characters")
public class RickAndMortyController {
    private final CharacterService client;

    @GetMapping("/random")
    @Operation(
            summary = "get random character",
            description = "get random character",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "success"
                    )}
    )
    public CharacterDto getRandomCharacter() {
        return client.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(
            summary = "get all characters containing name",
            description = "get all characters containing name",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "success"
                    )}
    )
    public List<CharacterDto> getCharacterByName(String name) {
        return client.findByPartOfName(name);
    }
}
