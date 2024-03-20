package mate.academy.rickandmorty.controller;

import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private CharacterService characterService;

    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacterDto();
    }
}
