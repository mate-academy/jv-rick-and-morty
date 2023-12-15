package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/characters")
public class RickAndMortyController {
    private final CharacterService characterService;

    @GetMapping
    public CharacterDto getWikiAboutRandomCharacter() {
        return characterService.getWikiAboutRandomCharacter();
    }

    @GetMapping("/search")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
