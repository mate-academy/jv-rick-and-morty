package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character management",
        description = "Endpoint for getting Rick and Morty characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get characters by names part",
            description = "Getting Rick and Morty characters by names part")
    public List<CharacterDto> getByNameContains(@RequestParam String name) {
        return characterService.findCharacterByName(name);
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get random character",
            description = "Getting random character from Rick and Morty API")
    public CharacterDto getRandomCharacter() {
        return characterService.findRandomCharacter();
    }
}
