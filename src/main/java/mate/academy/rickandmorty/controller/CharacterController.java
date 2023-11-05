package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CharacterController {
    private CharacterClient characterClient;
    private CharacterRepository characterRepository;

    @GetMapping
    public List<Character> getCharacters() {
        List<Character> characters = characterClient.getCharacters();
        characterRepository.saveAll(characters);
        return characters;
    }
}
