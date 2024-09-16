package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Сharacters management", description = "Endpoints for managing Сharacters")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharactersController {
    private final CharacterService characterService;

    @Operation(summary = "Get one random character")
    @GetMapping("/random")
    public CharacterEntity randomCharacter() {
        return characterService.findRandom();
    }

    @Operation(summary = "Search characters by name")
    @GetMapping
    public List<CharacterEntity> searchByName(String name) {
        return characterService.findByName(name);
    }
}
