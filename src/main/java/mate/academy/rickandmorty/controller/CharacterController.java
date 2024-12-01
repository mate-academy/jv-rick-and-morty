package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterClient characterClient;
    private final CharacterService characterService;

    @GetMapping("/get")
    @Operation(summary = "Get random character",
            description = "Get random character for universe Rick and Morty")
    public CharacterDto findById() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters", description = "Search characters by name")
    public List<CharacterDto> search(CharacterSearchParameters searchParameters) {
        return characterService.search(searchParameters);
    }

    @GetMapping("/test")
    public String testCharacter() {
        characterClient.getCharacter();
        return "Done!";
    }
}
