package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters management",
        description = "Endpoints for mapping characters from Rick and Morty")
@RequiredArgsConstructor
@RestController
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get a character",
            description = "Get a character by random id")
    @GetMapping("/random")
    public CharacterDto getCharacterById() {
        return characterService.getCharacterByRandomId();
    }

    @Operation(summary = "Search a character",
            description = "Get a list of character by name")
    @GetMapping("/search")
    public List<CharacterDto> search(String name) {
        return characterService.searchByName(name);
    }
}
