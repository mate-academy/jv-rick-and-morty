package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalResponseDto;
import mate.academy.rickandmorty.service.CharacterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Character searching",
        description = "Can use different options of searching characters"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterServiceImpl characterService;

    @Operation(summary = "get random", description = "get random character from DB")
    @GetMapping("/random")
    public InternalResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(
            summary = "search by param",
            description = "search characters by part of character name"
    )
    @GetMapping("/search")
    public List<InternalResponseDto> search(@RequestParam String param) {
        return characterService.getCharactersByParams(param);
    }
}
