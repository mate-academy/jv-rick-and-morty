package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick&Morty Controller", description = """
        Controller for managing Rick and Morty characters. 
        Provides endpoints to fetch a random 
        character or search characters by name.
        """)
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Fetch a random character", description = """
            Fetches a random character. If the character is not found in the local database, 
            it is fetched from the external API and saved locally. 
            If the character already exists in the database, it is retrieved from there.
            """)
    @GetMapping("/random")
    InternalCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search characters by name", description = """
            Searches for characters by their name. If a character 
            is not found in the local database, it is fetched from the external 
            API and saved locally.
             If characters already exist in the database, they are retrieved from there.
            """)
    @GetMapping("/search")
    List<InternalCharacterDto> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
