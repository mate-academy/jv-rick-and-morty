package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get random character", description =
            "Returns a random character from db")
    public CharacterResponseDto getRandom() {
        return characterService.getRandom();
    }

    @GetMapping("/{namePart}")
    @Operation(summary = "Find character by name", description =
            "Get a list of all characters whose name contains the search string from db")
    public List<CharacterResponseDto> findAllByNamePart(@PathVariable String namePart) {
        return characterService.findAllByNamePart(namePart);
    }
}
