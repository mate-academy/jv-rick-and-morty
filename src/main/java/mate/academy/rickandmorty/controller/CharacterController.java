package mate.academy.rickandmorty.controller;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterHandlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterHandlerService characterHandlerService;

    public CharacterController(CharacterHandlerService characterHandlerService) {
        this.characterHandlerService = characterHandlerService;
    }

    @GetMapping("/{name}")
    public List<CharacterDto> getCharacters(@PathVariable String name) {
        return characterHandlerService.getCharactersFromDatabase(name);
    }

    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterHandlerService.getRandomCharacter();
    }
}
