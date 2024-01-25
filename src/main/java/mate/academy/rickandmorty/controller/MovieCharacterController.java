package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.MovieCharacterDto;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "Endpoints for managing characters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "You can get random character")
    public MovieCharacterDto getRandomCharacter() {
        return movieCharacterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Find characters by name", description = "You can find characters by name")
    public List<MovieCharacterDto> findAllCharactersByName(
            @RequestParam(name = "name") String name) {
        return movieCharacterService.findAllCharactersByName(name);
    }
}
