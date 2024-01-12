package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Сharacters search", description = "character search operations")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get random Character", description = "get random Character from DB")
    @GetMapping
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacterFromDb();
    }

    @Operation(summary = "Find Characters by Name",
            description = "find Characters that match the input string")
    @GetMapping(value = "/search")
    public List<CharacterDto> searchCharactersByName(@RequestParam String name, Pageable pageable) {
        return characterService.findCharactersByName(name, pageable);
    }
}
