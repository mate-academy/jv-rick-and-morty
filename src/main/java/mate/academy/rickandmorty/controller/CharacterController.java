package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Viewing characters",
        description = "Endpoints for viewing Rick&Morty characters")
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Get a random Rick&Morty character with info about them")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get a list of characters by name",
            description = "Get a list of characters whose name contains the provided namePart")
    public List<CharacterResponseDto> getCharactersByName(@RequestParam String namePart) {
        return characterService.getCharactersByNamePart(namePart);
    }
}
