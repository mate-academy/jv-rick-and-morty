package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParams;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    @Autowired
    private final CharacterService characterService;

    @GetMapping
    CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping
    List<CharacterDto> search(@RequestParam CharacterSearchParams searchParams) {
        return characterService.search(searchParams);
    }
}
