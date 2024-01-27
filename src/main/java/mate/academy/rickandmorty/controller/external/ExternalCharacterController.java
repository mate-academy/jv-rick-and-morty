package mate.academy.rickandmorty.controller.external;

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.character.CharacterDto;
import mate.academy.rickandmorty.dto.external.character.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.external.character.CharactersDto;
import mate.academy.rickandmorty.service.client.CharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/character")
public class ExternalCharacterController {
    private final CharacterClient characterClient;

    @GetMapping
    @Operation(summary = "Get all characters",
            description = "Get all available characters + pagination")
    public CharactersDto getCharacters() throws IOException, InterruptedException {
        return characterClient.getCharacters();
    }

    @GetMapping("/{ids}")
    @Operation(summary = "Get characters by ids", description = "Get characters by ids")
    public List<CharacterDto> getCharactersByIds(@PathVariable Integer... ids)
            throws IOException, InterruptedException {
        return characterClient.getCharactersByIds(ids);
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by parameters",
            description = "Search characters by name, status, species, type, gender")
    public CharactersDto getCharactersByParams(CharacterSearchParameters characterSearchParameters)
            throws IOException, InterruptedException {
        return characterClient.getCharactersByParams(characterSearchParameters);
    }
}
