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

@Tag(name = "Characters", description = "Character API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharactersController {

    private final CharacterService characterService;

    @Operation(summary = "Get random character wiki")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.generateRandomCharacterWikiDto();
    }

    @Operation(summary = "Get character by name")
    @GetMapping
    public List<CharacterDto> getCharactersByName(@RequestParam(required = false) String name) {
        return characterService.findCharactersByName(name);
    }
}

