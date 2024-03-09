package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(summary = "Get a random character",
            description = "You can get a random person's, there are "
                    + "826 characters in total, so you can try to find your favorite one =)")
    @GetMapping("/get_random_character")
    public CharacterDto getById() {
        return rickAndMortyService.getById(new Random().nextLong(826));
    }

    @Operation(summary = "Get characters by name", description = "You can enter a name or part "
            + "of it if you do not remember it completely and you will receive "
            + "a list of all characters that match the entered data")
    @GetMapping("/by-name")
    public List<CharacterDto> getByName(@RequestParam String name) {
        return rickAndMortyService.getByName(name);
    }
}
