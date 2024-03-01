package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.response.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty characters", description = "Endpoints for characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/search")
    @Operation(
            summary = "Get all character by name",
            description = "Get all characters containing the search name"
    )
    public List<CharacterResponseDto> search(@RequestParam String name) {
        return characterService.getCharactersByNameContaining(name);
    }
    
    @GetMapping("/random")
    @Operation(summary = "Get a random character")
    public CharacterResponseDto getRandom() {
        return characterService.getRandomCharacter();
    }
    
}
