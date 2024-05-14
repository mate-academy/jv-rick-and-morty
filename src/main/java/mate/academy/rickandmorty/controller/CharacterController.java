package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
@Tag(name = "Rick and Morty API management")
public class CharacterController {
    private final CharacterService service;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Search for a character by name",
            description = "Returns list of characters by name or name part")
    public List<CharacterDto> getCharactersByName(@RequestParam String name, Pageable pageable) {
        return service.getAllCharactersByName(name, pageable);
    }

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Search for a random character",
            description = "Returns a random character")
    public CharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }
}
