package mate.academy.rickandmorty.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rick and Morty management", description = "Endpoints for managing movie characters")
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;

    @GetMapping("/random")
    @Operation(description = "Get random movie character")
    @ResponseStatus(HttpStatus.OK)
    public MovieCharacterResponseDto getRandom() {
        return movieCharacterService.getRandom();
    }

    @GetMapping("/namepart")
    @Operation(description = "Get list of movie characters by part of first name")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieCharacterResponseDto> getByNameLike(@RequestParam String namepart) {
        return movieCharacterService.findByNameLike(namepart);
    }
}
