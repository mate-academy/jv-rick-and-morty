package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.service.impl.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Rick and Morty API", description = "API endpoints for movie characters")
public class ApiController {
    @Autowired
    private ApiClient client;

    @Operation(summary = "Get random movie character",
            description = "Get a random movie character",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            })
    @GetMapping("/find-random")
    public MovieCharacterResponseDto getRandomCharacter() {
        return client.getRandomCharacter();
    }

    @Operation(summary = "Get movie characters by name",
            description = "Get a list of movie characters by name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            },
            parameters = {
                    @Parameter(
                            name = "name",
                            description = "Name of the movie character",
                            example = "Cynthia"
                    )
            })
    @GetMapping("/find-by-name/{name}")
    public List<MovieCharacterResponseDto> getByName(@RequestParam String name) {
        return client.getByName(name);
    }
}
