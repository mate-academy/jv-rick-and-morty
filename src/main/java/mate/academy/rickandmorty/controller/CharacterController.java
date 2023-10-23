package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.internal.CharacterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
@Tag(name = "Rick&Morty Wiki", description = "All characters from the cartoon in one place ;)")
public class CharacterController {
    private final CharacterService service;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Return random character from the Rick&Morty universe")
    public CharacterResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/")
    @Operation(summary = "Get list of characters where name contains passed parameter",
            description = "Find and return all characters where name contains passed parameter")
    public List<CharacterResponseDto> getAllByContainingName(
            @ParameterObject Pageable pageable, @RequestParam String name) {
        return service.findAllByNameContaining(name, pageable);
    }
}
