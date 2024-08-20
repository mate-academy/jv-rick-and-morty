package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParameters;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters", description = "Endpoint for mapping books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Gen random character", description = "Getting information about random "
            + "character from db")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Find characters by name", description = "Finding characters by "
            + "parameter name")
    @GetMapping("/search")
    public List<CharacterDto> searchCharacters(CharacterSearchParameters searchParameters) {
        return characterService.search(searchParameters);
    }
}
