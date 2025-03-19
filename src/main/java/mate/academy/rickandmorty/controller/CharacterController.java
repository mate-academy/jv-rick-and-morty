package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
@Tag(name = "Character API", description = "API for managing and searching characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDto> getCharacters(@RequestParam(required = false) String name) {
        return characterService.searchCharacters(name);
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }
}
