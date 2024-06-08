package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
@Tag(name = "Rick and Morty management")
public class RickAndMortyController {
    private final CharacterService characterService;

    @Operation(summary = "Endpoint to get random rick and morty character")
    @GetMapping
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Endpoint to get rick and morty character by part of name")
    @GetMapping("/by-name")
    public List<CharacterDto> getCharacter(@RequestParam String name) {
        return characterService.findByNamePart(name);
    }
}
