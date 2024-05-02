package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    public List<CharacterDto> getCharacter(@RequestParam String namePart) {
        return characterService.findByNamePart(namePart);
    }
}
