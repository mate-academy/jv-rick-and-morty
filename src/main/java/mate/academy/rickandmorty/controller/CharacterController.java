package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty", description = "Endpoints for retrieving some characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rickAndMorty/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Get random character from internal db")
    public CharacterWikiDto generateRandomCharacter() {
        return characterService.generateRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Get a list of characters by argument",
            description = "Get a list of characters by incoming name argument")
    public List<CharacterWikiDto> searchCharactersByNameArgument(
            @RequestParam String nameArgument
    ) {
        return characterService.searchAllCharactersByNameArgument(nameArgument);
    }
}
