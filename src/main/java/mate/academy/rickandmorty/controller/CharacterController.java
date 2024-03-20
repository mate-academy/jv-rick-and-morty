package mate.academy.rickandmorty.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "For managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "You get a random character")
    public CharacterResponseDto getRandomCharacters() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Find characters by name",
            description = "You can find characters by name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String name) {
        return characterService.findAllByName(name);
    }

    @GetMapping
    @Operation(summary = "Get all characters",
            description = "You get all characters, default parameters: page=0, size=20, sort=id")
    public List<CharacterResponseDto> findAll(Pageable pageable) {
        return characterService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find character by id",
            description = "You can find character by id")
    public CharacterResponseDto findById(@PathVariable Long id) {
        return characterService.findById(id);
    }
}
