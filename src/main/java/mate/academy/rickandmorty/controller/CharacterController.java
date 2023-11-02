package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Character management", description = "Endpoints for getting characters from db")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get random character",
            description = "Get a random characters from Rick and Morty world")
    public CharacterDto getRandomChar() {
        return characterService.getRandomCharacter();
    }

    @GetMapping(value = "/find")
    @Operation(summary = "Find characters by name's part",
            description = "Find characters by name's part")
    public List<CharacterDto> findByNameContains(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
