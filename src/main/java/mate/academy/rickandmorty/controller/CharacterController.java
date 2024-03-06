package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Find characters by name.",
            parameters = {@Parameter(required = true,
                    description = "Name of a character. Allows low case name.")})
    @GetMapping("/find")
    public List<Character> findByName(@RequestParam String name) {
        return characterService.findAllByName(name);
    }

    @GetMapping("/find-random")
    public Character findRandom() {
        return characterService.findRandom();
    }
}
