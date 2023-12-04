package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService service;  
    
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping
    public List<CharacterDto> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
}
