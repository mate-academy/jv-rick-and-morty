package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterRickAndMortyDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick And Morty =)", description = "If you like Rick And Morty and are interested"
        + " in learning about ALL the characters please use my app")
@RestController
@RequestMapping("/rick_and_morty_characters")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final RickAndMortyService rickAndMortyService;

    @Operation(summary = "Get a certain character by ID",
            description = "You can get with the help of a certain person's ID, there are "
                    + "826 characters in total, so you can try to find your favorite one =)")
    @GetMapping("/{id}")
    public CharacterRickAndMortyDto getById(@PathVariable Long id) {
        return rickAndMortyService.getById(id);
    }

    @Operation(summary = "Get characters by name", description = "You can enter a name or part "
            + "of it if you do not remember it completely and you will receive "
            + "a list of all characters that match the entered data")
    @GetMapping("/by-name")
    public List<CharacterRickAndMortyDto> getByName(@RequestParam String name) {
        return rickAndMortyService.getByName(name);
    }
}
