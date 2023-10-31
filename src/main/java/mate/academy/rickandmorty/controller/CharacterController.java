package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management",
        description = "Endpoints for receiving Rick and Morty characters from DB")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Getting random character",
            description = "Randomly generates a wiki about one character "
                    + "in the universe the animated series Rick & Morty")
    @GetMapping(value = "/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Getting character by name containing string",
            description = "Returns a list of all characters "
                    + "whose name contains the search string")
    @GetMapping(value = "/search")
    public List<CharacterDto> searchCharactersByName(
            @ParameterObject @PageableDefault(size = 5) Pageable pageable,
            @RequestParam String name) {
        return characterService.getCharacterByName(name, pageable);
    }
}
