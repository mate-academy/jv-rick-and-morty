package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty characters API", description = "Api that gives ability to "
        + "get information about rick and morty characters")
public class RickAndMortyController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character wiki", description = "Get random character wiki")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get all character that contains substring in name",
            description = "Get all character that contains substring in name")
    public List<CharacterResponseDto> getCharactersByName(@RequestParam(defaultValue = "rick")
                                                              String name) {
        return characterService.searchCharacterByName(name);
    }
}
