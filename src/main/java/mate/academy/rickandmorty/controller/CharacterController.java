package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClientImpl;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CharacterController {
    private CharacterService characterService;

    @GetMapping
    public List<Character> getCharacters() {
        return characterService.getAll();
    }

    @GetMapping("/search")
    public List<Character> getCharactersByName(String name) {
        return characterService.getCharactersByName(name);
    }

    @GetMapping("/random")
    public Character saveRandomCharacter() {
        Character character = characterService.saveRandomCharacter();
        return character;

    }
}
