package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty Character Management",
        description = "Endpoints for fetching and searching movie characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Retrieves one random movie character from the local database")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved random character")
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterService.getRandomCharacter());
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters by name",
            description = "Finds characters whose names contain the specified string")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching characters")
    public List<CharacterDto> searchCharacter(@RequestParam String name) {
        return characterService.findAllByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
