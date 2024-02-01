package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.character.CharacterDto;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String getCharactersData() {
        characterService.saveAllCharacters();
        return "Done!";
    }

    @GetMapping("/random")
    public CharacterDto getRandom() {
        return characterService.randomCharacter();
    }

    @GetMapping("/filter")
    public List<CharacterDto> findByName(@RequestParam String name) {
        return characterService.findByName(name);
    }
}
