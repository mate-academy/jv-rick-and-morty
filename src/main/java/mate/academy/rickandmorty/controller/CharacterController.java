package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
@Tag(name = "Rick and Morty management")
public class CharacterController {
    private final CharacterService service;

    @Operation(summary = "Get by name",
            description = "Returns list of characters by name or name part")
    @GetMapping("/by-name")
    public List<ResponseDto> getCharactersByName(@RequestParam String name, Pageable pageable) {
        return service.getCharactersByName(name, pageable);
    }

    @Operation(summary = "Get random character from DB",
            description = "Returns random character from DB")
    @GetMapping("/random")
    public ResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }
}
