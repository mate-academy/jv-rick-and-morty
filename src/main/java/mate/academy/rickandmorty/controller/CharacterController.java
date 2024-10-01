package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.service.character.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller",
        description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Get a random character from internal database")
    public ResponseCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search character",
            description = "Search for a character in the database by part of the name")
    public List<ResponseCharacterDto> searchCharacter(String name, Pageable pageable) {
        return characterService.findAllByName(name, pageable);
    }
}
