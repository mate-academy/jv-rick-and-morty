package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/by-name")
    @Operation(summary = "Get list character", description = "Find all by name")
    public List<CharacterEntity> findAllByName(@RequestParam String name) {
        return characterService.findAllByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Get random character")
    public CharacterEntity findRandomCharacter() {
        return characterService.getRandom();
    }

}
