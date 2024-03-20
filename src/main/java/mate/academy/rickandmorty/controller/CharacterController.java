package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character", description = "Get a random character from Rick and Morty")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacterDto();
    }
}
