package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.RickCharacterDto;
import mate.academy.rickandmorty.service.RickCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class RickCharacterController {
    private final RickCharacterService service;

    @GetMapping("/random")
    public RickCharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/search")
    public List<RickCharacterDto> searchCharacters(@RequestParam String name) {
        return service.searchCharacters(name);
    }
}

