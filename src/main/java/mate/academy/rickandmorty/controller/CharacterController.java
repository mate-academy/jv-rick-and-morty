package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CartoonCharacterService characterService;

    @GetMapping
    public List<CartoonCharacter> getAll(@RequestParam(defaultValue = "") String name) {
        return characterService.getAll(name);
    }

    @GetMapping("/random")
    public CartoonCharacter getRandom() {
        return characterService.getRandomCharacter();
    }
}
