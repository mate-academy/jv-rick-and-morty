package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty Character management", description = "Endpoints for managing character")
@RequiredArgsConstructor
@RequestMapping("/characters")
@RestController
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get character", description = "Retrieve randomly "
            + "generates a wiki about one character in the universe the animated "
            + "series Rick & Morty")
    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search character by name", description = "Returns a list "
            + "of all characters whose name contains the search string")
    @GetMapping("/search")
    public List<CharacterResponseDto> findByName(
            @ParameterObject @PageableDefault Pageable pageable,
            @RequestParam String name
    ) {
        return characterService.findAllByName(pageable, name);
    }
}
