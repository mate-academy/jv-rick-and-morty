package mate.academy.rickandmorty.controller.external;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mate.academy.rickandmorty.service.external.CharacterClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management",
        description = "Endpoint for fetching characters from The Rick and Morty API"
                + " and adding them to DB")
@RestController
@RequestMapping("/character")
public class CharacterExternalController {
    private final CharacterClient characterClient;

    public CharacterExternalController(CharacterClient characterClient) {
        this.characterClient = characterClient;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Save all characters",
            description = "Fetch characters from The Rick and Morty API and add them to DB")
    @GetMapping("/fetchAndSaveAll")
    public void fetchAllCharacters() {
        characterClient.fetchAndSaveAllCharacters();
    }
}
