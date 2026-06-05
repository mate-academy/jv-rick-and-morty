package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character management", description = "Endpoinds to get characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Find character by name")
    public List<CharacterResponseDto> findAllByName(@RequestParam String name) {
        return characterService.findAllByName(name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }
}
