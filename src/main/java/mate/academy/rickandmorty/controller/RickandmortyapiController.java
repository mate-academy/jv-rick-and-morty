package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty management", description = "Endpoints for managing characters")
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class RickandmortyapiController {
    private final CharacterService characterService;

    @Operation(summary = "Get random", description = "get random hero from all characters")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Get by name", description = "get all characters by name")
    @GetMapping("/search")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.findAllByName(name);
    }
}
