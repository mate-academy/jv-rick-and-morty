package mate.academy.rickandmorty.controller;

import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CharacterController {
    private CharacterClient characterClient;

    @GetMapping
    public List<Character> getCharacters() {
        List<Character> characters = characterClient.getCharacters();
        return characters;
    }
}
