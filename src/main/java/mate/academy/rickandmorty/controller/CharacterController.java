package mate.academy.rickandmorty.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @ApiOperation(value = "Generate a random character from Rick and Morty universe")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    @ApiOperation(value = "Search characters by name")
    public List<Character> getCharactersByNames(@RequestParam String names) {
        return characterService.getCharactersByNames(names);
    }
}
