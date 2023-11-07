package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty API")
@RestController
@RequestMapping("/api/characters")
@AllArgsConstructor
public class CharacterController {
    private CharacterService characterService;

    @Operation(summary = "Get all available characters.")
    @GetMapping
    public List<CharacterDto> getCharacters() {
        return characterService.getAll();
    }

    @Operation(summary = "Find characters by specific name.")
    @GetMapping("/search")
    public List<CharacterDto> getCharactersByName(String name) {
        return characterService.getCharactersByName(name);
    }

    @Operation(summary = "Generate and save random existing character.")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        CharacterDto character = characterService.getRandomCharacter();
        return character;

    }
}
