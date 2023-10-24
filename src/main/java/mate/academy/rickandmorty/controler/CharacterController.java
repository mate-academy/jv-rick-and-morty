package mate.academy.rickandmorty.controler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterSearchParametersDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Endpoints for managing characters")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/characters")
@OpenAPIDefinition
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/character")
    @Operation(summary = "Get one Character", description = "The request randomly generates "
            + "a wiki about one character in the universe the animated series Rick & Morty.")
    public CharacterDto findByRandomId() {
        return characterService.findByRandomId();
    }

    @GetMapping("/search/name")
    @Operation(summary = "Find all Characters by name", description =
            "Get a list of all Characters according to the name")
    List<CharacterDto> searchCharactersByName(CharacterSearchParametersDto searchParametersDto,
                                        Pageable pageable) {
        return characterService.searchCharactersByName(searchParametersDto, pageable);
    }

    @GetMapping
    @Operation(summary = "Get all characters", description = "Get a list of all "
            + "available characters")
    public List<CharacterDto> getAll(Pageable pageable) {
        return characterService.getAll(pageable);
    }
}
