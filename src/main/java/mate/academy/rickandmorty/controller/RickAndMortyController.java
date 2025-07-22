package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty", description = "An operation involving getting a random person "
        + "and searching for a person by name from the animated series Rick and Morty")
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class RickAndMortyController {

    private final RickAndMortyService service;

    @Operation(summary = "get random characters", description = "get random character fro DB")
    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @Operation(summary = "search characters", description = "search for characters by name")
    @GetMapping("/search")
    public List<CharacterDto> search(CharacterSearchParametersDto searchParametersDto) {
        return service.search(searchParametersDto);
    }
}



