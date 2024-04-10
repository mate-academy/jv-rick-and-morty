package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
@Tag(name = "Character Controller", description = "Endpoints for managing characters")
public class CharacterController {
    private final CharacterService service;

    @Operation(summary = "Generates wiki about a random character")
    @ApiResponse(responseCode = "200", description = "Character returned successfully", content = {
            @Content(mediaType = "application/json", schema
                    = @Schema(implementation = CharacterResponseDto.class),
                    examples = @ExampleObject(value = "{\n"
                            + "    \"id\": 240,\n"
                            + "    \"externalId\": 240,\n"
                            + "    \"name\": \"Mr. Goldenfold\",\n"
                            + "    \"status\": \"Alive\",\n"
                            + "    \"species\": \"Human\",\n"
                            + "    \"gender\": \"Male\"\n"
                            + "}"))})
    @GetMapping("/random")
    public CharacterResponseDto generateRandom() {
        return service.generateRandomCharacter();
    }

    @Operation(summary = "Returns all characters whose names contain the given string")
    @ApiResponse(responseCode = "200",
            description = "List of characters returned successfully", content = {
                @Content(mediaType = "application/json", schema
                    = @Schema(implementation = CharacterResponseDto.class),
                    examples = @ExampleObject(value = "[\n"
                            + "    {\n"
                            + "        \"id\": 654,\n"
                            + "        \"externalId\": 654,\n"
                            + "        \"name\": \"Plane Crash Survivor\",\n"
                            + "        \"status\": \"unknown\",\n"
                            + "        \"species\": \"Human\",\n"
                            + "        \"gender\": \"Male\"\n"
                            + "    },\n"
                            + "    {\n"
                            + "        \"id\": 653,\n"
                            + "        \"externalId\": 653,\n"
                            + "        \"name\": \"Plane Crash Survivor\",\n"
                            + "        \"status\": \"unknown\",\n"
                            + "        \"species\": \"Human\",\n"
                            + "        \"gender\": \"Female\"\n"
                            + "    }\n"
                            + "]"))})

    @GetMapping("/search/by-name")
    public List<CharacterResponseDto> searchByName(
            @Parameter(description = "search string")
            @RequestParam String name,
            @Parameter(description = "Pageable object")
            Pageable pageable) {
        return service.searchCharactersByName(name, pageable);
    }
}
