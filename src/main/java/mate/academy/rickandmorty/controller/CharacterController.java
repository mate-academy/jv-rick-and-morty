package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rick-and-morty")
public class CharacterController {
    private final CharacterService characterService;
    
    @GetMapping("/random")
    public CharacterDto findRandomCharacter() {
        return characterService.getRandomCharacter();
    }
    
    @GetMapping("/{name}")
    public List<CharacterDto> findCharactersWithNameLike(@PathVariable String name) {
        return characterService.getCharactersByName(name);
    }
}
