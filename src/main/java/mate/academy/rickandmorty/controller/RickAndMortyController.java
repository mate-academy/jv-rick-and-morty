package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyClientImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "R&M Characters API",
        description = "Endpoints for searching the characters of the universe Rick & Morty"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class RickAndMortyController {
    private final RickAndMortyClientImpl rickAndMortyClientImpl;

    @GetMapping
    @Operation(
            summary = "Receive a random character",
            description = "This endpoint receives characters with help of random"
    )
    public CharacterDto getRandomCharacter() {
        return rickAndMortyClientImpl.getRandomCharacter();
    }

    @GetMapping("/name={string}")
    @Operation(
            summary = "Receive the characters",
            description = "This endpoint receives the characters by their partial or full name"
    )
    public List<CharacterDto> getCharacterByName(@PathVariable String string) {
        return rickAndMortyClientImpl.getCharacterByName(string);
    }
}
