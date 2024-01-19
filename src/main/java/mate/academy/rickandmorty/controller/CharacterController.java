package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rickAndMorty/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    public CharacterWikiDto generateRandomCharacter() {
        return characterService.generateRandomCharacter();
    }

    @GetMapping("/search")
    public List<CharacterWikiDto> searchCharactersByNameArgument(
            @RequestParam String nameArgument
    ) {
        return characterService.searchAllCharactersByNameArgument(nameArgument);
    }
}
