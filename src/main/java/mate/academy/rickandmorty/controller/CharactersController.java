package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharactersController {

    private final CharacterService characterService;

    @GetMapping("/random")
    public CharacterWikiDto getRandomCharacter() {
        return characterService.generateRandomCharacterWikiDto();
    }
}

