package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Rick and Morty character management", description = "Endpoints for managing Rick and Morty characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/by-name")
    @Operation(summary = "Get a character by name", description = "Get a character by name")
    public List<CharacterInternalResponseDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get a random character", description = "Get a random character")
    public CharacterInternalResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }
}
