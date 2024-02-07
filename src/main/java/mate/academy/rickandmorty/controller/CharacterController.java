package mate.academy.rickandmorty.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "Endpoints for managing characters")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/character")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "You can get a random character")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Find characters by name",
            description = "You can find characters by name")
    public List<CharacterDto> getCharacterByName(@PathVariable String name) {
        return characterService.getCharacterListByName(name);
    }
}
