package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterSearchParametersDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacter;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character manager", description = "Endpoints for managing characters")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterServiceImpl characterService;

    @GetMapping("/random")
    @Operation(summary = "Get character", description = "Get a random character from everyone")
    public ExternalCharacter getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Get search character",
            description = "Get all characters according to the search data")
    public List<ExternalCharacter> searchCharacter(
            CharacterSearchParametersDto searchParametersDto, Pageable pageable
    ) {
        return characterService.searchCharacters(searchParametersDto, pageable);
    }
}
