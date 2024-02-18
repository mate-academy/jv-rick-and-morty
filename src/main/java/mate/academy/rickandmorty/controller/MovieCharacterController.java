package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters", description = "Endpoint for operations with character")
@RestController
@RequestMapping("/movie-characters")
@RequiredArgsConstructor
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final CharacterMapper mapper;

    @GetMapping("/random")
    @Operation(
            summary = "Get a random character",
            description = "Get a random character"
    )
    public CharacterResponseDto getRandom() {
        MovieCharacter character = movieCharacterService.getRandomCharacter();
        return mapper.toResponseDto(character);
    }

    @GetMapping("/by-name")
    @Operation(
            summary = "Find characters by name part",
            description = "Find characters by name part"
    )
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return movieCharacterService.findAllByNameContainsIgnoreCase(namePart)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
