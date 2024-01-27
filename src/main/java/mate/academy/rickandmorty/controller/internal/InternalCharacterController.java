package mate.academy.rickandmorty.controller.internal;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.server.CharacterServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/character")
public class InternalCharacterController {
    private final CharacterServer characterServer;

    @GetMapping("/by-name")
    @Operation(summary = "Get characters by name", description = "Get list of characters by name")
    List<Character> getCharactersByName(String name) {
        return characterServer.findByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Get random character from db")
    Character getRandomCharacter() {
        return characterServer.getRandomCharacter();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get character by id", description = "Get character by id")
    Character getCharacterById(@PathVariable Integer id) {
        return characterServer.findById(id);
    }
}
