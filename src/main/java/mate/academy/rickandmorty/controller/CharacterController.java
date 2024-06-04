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

@Tag(name = "Character management", description = "Endpoints to get characters frim Rick and Morty")
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService service;

    @Operation(summary = "Get list characters", description = "Get list characters by name from db")
    @GetMapping("/search")
    public List<CharacterDto> getAllByName(@RequestParam String name) {
        return service.getAllByName(name);
    }

    @Operation(summary = "Get character", description = "Get random character from db")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return service.getRandom();
    }
}
