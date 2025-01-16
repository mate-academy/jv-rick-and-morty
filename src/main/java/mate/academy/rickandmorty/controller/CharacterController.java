package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterConversionDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Operations with characters")
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService service;

    @GetMapping
    @Operation(summary = "Get random character", description = "Get random character")
    public CharacterConversionDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters", description = "Search characters")
    public List<CharacterConversionDto> searchCharacters(
            @RequestParam
            String searchParameter) {
        return service.searchCharacters(searchParameter);
    }
}
