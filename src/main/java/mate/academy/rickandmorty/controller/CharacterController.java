package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/characters")
@RequiredArgsConstructor
@Tag(name = "Api for searching characters of Rick and Morty series",
        description = "Helps to find characters by name or provides a random character")
public class CharacterController {
    private final ClientService clientService;

    @GetMapping(value = "/random")
    @Operation(summary = "Random character", description = "Provides a random character")
    public Character randomCharacter() {
        return clientService.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Get a character by name",
            description = "Provides a list of characters by providing string")
    public List<Character> getCharactersBySequence(String name) {
        return clientService.getCharactersWithNameContaining(name);
    }
}
