package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterClient characterClient;

    @GetMapping("/random")
    public CartoonCharacter getRandomCharacter() {
        return characterClient.getRandomCharacter();
    }

    @GetMapping("/search")
    public List<CartoonCharacter> searchCharacters(String name) {
        return characterService.search(name);
    }
}
