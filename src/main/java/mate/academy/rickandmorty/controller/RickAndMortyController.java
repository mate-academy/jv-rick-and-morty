package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@Tag(name = "Characters", description = "Founding character by name or id")
public class RickAndMortyController {
    private final CharacterService characterService;

    public RickAndMortyController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    public List<CharacterResponseDto> getCharactersByName(@RequestParam("name") String name) {
        return characterService.getCharactersByName(name);
    }
}
