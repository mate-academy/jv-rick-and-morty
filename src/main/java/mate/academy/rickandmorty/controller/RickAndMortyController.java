package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.impl.RickAndMortyInternalServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Rick and Morty",
        description = "Endpoints for getting random character from the Rick and morty universe"
                + " or searching character by name"
)
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final RickAndMortyInternalServiceImpl rickAndMortyInternalServiceImpl;

    @Operation(
            summary = "Get character by name",
            description = "API for getting character by name with ignore case"
    )
    @GetMapping("/by-name")
    public List<CharacterDto> getCharactersByName(@RequestParam String name) {
        return rickAndMortyInternalServiceImpl.getAllCharactersNameLike(name);
    }

    @Operation(
            summary = "Get random caracter",
            description = "API for getting random character"
    )
    @GetMapping(path = "/random-character")
    public CharacterDto getRandomCharacter() {
        return rickAndMortyInternalServiceImpl.getRandomCharacter();
    }
}
