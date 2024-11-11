package mate.academy.rickandmorty.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.ResponceCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    public ResponceCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    public List<ResponceCharacterDto> searchCharactersByName(@RequestParam String name) {
        return characterService.searchCharactersByName(name);
    }
}
