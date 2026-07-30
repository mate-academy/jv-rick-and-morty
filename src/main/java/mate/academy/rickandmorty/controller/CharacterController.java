package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick & Morty characters API", description = "API for " 
        + "getting information about characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/rick-and-morty/characters")
public class CharacterController {
    private final CharacterService characterService;
    
    @Operation(summary = "get random character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns character"),
            @ApiResponse(responseCode = "404", description = "character not found")}
    )
    @GetMapping("/random")
    public CharacterDto findRandomCharacter() {
        return characterService.getRandomCharacter();
    }
    
    @Operation(summary = "get character by name", description = "returns characters " 
            + "with similar names")
    @GetMapping("/{name}")
    public List<CharacterDto> findCharactersWithNameLike(@PathVariable String name) {
        return characterService.getCharactersByName(name);
    }
}
