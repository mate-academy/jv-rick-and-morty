package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharactersController {
    private final CharacterService characterService;

    @GetMapping("/random")
    public CharacterInternalDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    public List<CharacterInternalDto> getAllByNamePart(@RequestParam String namePart) {
        return characterService.getAllByNamePart(namePart);
    }

}
