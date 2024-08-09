package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParameters;
import mate.academy.rickandmorty.service.CharacterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty managment", description = "Endpoints for managing characters")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharactersController {
    private final CharacterServiceImpl characterService;

    @GetMapping("/all")
    public List<CharacterDto> getAll() {
        return characterService.getAll();
    }

    @Operation(summary = "Get random charater", description = "Get random charater from DB")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Find character by name", description = "Find character by name from DB")
    @GetMapping
    public List<CharacterDto> search(CharacterSearchParameters characterSearchParameters) {
        return characterService.search(characterSearchParameters);
    }
}
