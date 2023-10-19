package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;
    
    @GetMapping("/characters/{id}")
    public CharacterResponseDto getCharacter(@PathVariable Long id) {
        return characterService.getCharacter(id);
    }
}
