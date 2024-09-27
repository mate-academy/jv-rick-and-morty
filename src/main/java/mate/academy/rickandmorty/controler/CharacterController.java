package mate.academy.rickandmorty.controler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters repository manager",
        description = "This managers have basic tool "
                + "for searching and watching character description")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/search")
    @Operation(summary = "Search by name (with pagination and sorting)",
            description = "return list of characters which name contains the search string")
    public List<CharacterDto> searchByName(String name, Pageable pageable) {
        return characterService.findByName(pageable, name);
    }

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "returns a random hero that is available in the database")
    public CharacterDto getRandom() {
        return characterService.getRandomCharacter();
    }
}
