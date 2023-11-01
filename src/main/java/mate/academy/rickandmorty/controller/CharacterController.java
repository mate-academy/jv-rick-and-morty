package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterRespondDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick&Morty character's random Wiki",
        description = "Mini-version of https://rickandmortyapi.com/api/character")
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get a random character properties")
    @GetMapping("/random")
    public CharacterRespondDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

}
