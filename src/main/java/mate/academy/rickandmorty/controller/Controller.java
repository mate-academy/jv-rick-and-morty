package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterShortInfoDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rick-and-morty")
@RequiredArgsConstructor
public class Controller {
    private final RickAndMortyService rickAndMortyService;

    @GetMapping("/random-character")
    public CharacterShortInfoDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    public List<CharacterShortInfoDto> getByName(@RequestParam String name) {
        return rickAndMortyService.findByNameContainingIgnoreCase(name);
    }
}
