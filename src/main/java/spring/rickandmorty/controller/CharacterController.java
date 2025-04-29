package spring.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.rickandmorty.model.Character;
import spring.rickandmorty.service.CharacterService;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character")
    public spring.rickandmorty.model.Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name")
    public List<Character> search(@RequestParam String name) {
        return characterService.searchCharactersByName(name);
    }
}
