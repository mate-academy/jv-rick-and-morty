package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Character Controller",
        description = "This class is responsible for managing some endpoints "
                + "related to characters.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CharacterController {
    private final CharacterService characterService;
    private final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    @Tag(
            name = "Get random character",
            description = "Get a random character")
    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        logger.info("Received a request to get a random character.");
        return characterService.getRandomCharacter();
    }

    @Tag(
            name = "find character",
            description = "Search characters by name's")
    @GetMapping("/search")
    @Operation(summary = "Search characters by name's part",
            description = "Search characters by name's part")
    public List<CharacterResponseDto> searchCharacter(@RequestParam String name) {
        logger.info("Received a search request for characters by name: {}", name);
        List<CharacterResponseDto> result = characterService.findByName(name);
        logger.info("Found {} characters matching the search criteria.", result.size());
        return result;
    }
}

