package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty character management",
        description = "Endpoints for managing Rick and Morty characters")
@RestController
@RequestMapping(value = "/api/characters", produces = "application/json")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get random character",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Random character found")})
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandom();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of characters with matches name",
            parameters = @Parameter(in = ParameterIn.QUERY, name = "name",
                    description = "Full or partial name of the characters"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of characters by name")})
    public List<CharacterResponseDto> getByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }
}
