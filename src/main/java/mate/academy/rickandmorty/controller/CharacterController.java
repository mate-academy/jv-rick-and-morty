package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Character Controller",
        description = "Endpoints for managing characters."
)
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(
            summary = "Generates wiki about random character."
    )
    public CharacterDto getRandom() {
        return characterService.getRandom();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Returns list of characters where name contains given in"
                    + "params string.",
            description = "Example: .../api/search?name=your_string&page=your_page. "
                    + "Please, pay attention that you need to change 'sort' "
                    + "parameter 'string' to 'asc'"
    )
    public List<CharacterDto> getAllByNameContains(
            @RequestParam String name,
            Pageable pageable
    ) {
        return characterService.getAllByNameContains(name, pageable);
    }
}
