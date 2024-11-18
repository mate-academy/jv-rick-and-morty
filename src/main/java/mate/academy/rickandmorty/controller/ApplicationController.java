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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Application", description = "Endpoints for application")
@RequiredArgsConstructor
@RestController
@RequestMapping("/application")
public class ApplicationController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(
            summary = "Get character",
            description = "The request randomly generates a wiki about one character"
                    + " in the universe the animated series Rick & Morty")
    public CharacterDto getCharacter(@ParameterObject @PageableDefault Pageable pageable) {
        return characterService.getCharacter(pageable);
    }

    @GetMapping("/{name}")
    @Operation(
            summary = "Get character by name",
            description = "returns a list of all characters whose"
                    + " name contains the search string")
    public List<CharacterDto> getCharacterByName(@PathVariable String name,
                                                 @ParameterObject @PageableDefault
                                                 Pageable pageable) {
        return characterService.findCharacterByName(name, pageable);
    }
}
