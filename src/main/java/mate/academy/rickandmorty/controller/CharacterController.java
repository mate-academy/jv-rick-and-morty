package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Character management",
        description = "Endpoints for getting more information about characters"
)
@RestController
@RequestMapping("characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(
            summary = "Get a character by a random id",
            description = "Allow to get a random character"
    )
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search for characters by their names",
            description = "Allow to search for character by their names "
    )
    public List<CharacterResponseDto> search(Pageable pageable, @RequestParam String name) {
        return characterService.search(pageable, name);
    }
}
