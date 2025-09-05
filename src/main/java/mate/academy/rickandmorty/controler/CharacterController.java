package mate.academy.rickandmorty.controler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rick & Morty")
public class CharacterController {
    private final CharacterService service;

    @Operation(summary = "Random character (from DB)")
    @GetMapping("/api/characters/random")
    public CharacterDto random() {
        return service.getRandomCharacter();
    }

    @Operation(summary = "Search characters by name")
    @GetMapping("/api/characters/search")
    public List<CharacterDto> search(@RequestParam("q") String q) {
        return service.findCharactersByName(q);
    }
}
