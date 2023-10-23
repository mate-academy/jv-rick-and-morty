package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.impl.CharacterClientImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Rick&Morty API",
        description = "Endpoints for receiving different"
                + " characters from the fantastic universe."
)
@RestController
@RequestMapping("character")
@RequiredArgsConstructor
public class GetCharacterController {
    private final CharacterService characterService;
    private final CharacterClientImpl client;

    @GetMapping()
    @Operation(summary = "Get random character.",
            description = "Allows the user get one random character.")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search/name={value}")
    @Operation(summary = "Search character by name.",
            description = "Allows the user to find special character by name.")
    public List<CharacterResponseDto> getCharacterByName(@PathVariable String value) {
        return characterService.findCharacterByInputValue(value);
    }
}
