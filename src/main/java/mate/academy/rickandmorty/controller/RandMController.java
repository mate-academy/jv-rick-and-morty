package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.PreparedResponseToController;
import mate.academy.rickandmorty.dto.CharacterOfRickyAndMortyDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters getter",
        description = "Endpoints for Rick and Morty characters getter")
@RestController
@RequestMapping("/randm")
@RequiredArgsConstructor
public class RandMController {

    private final PreparedResponseToController preparedResponseToController;

    @GetMapping
    @Operation(summary = "Get random character of Rick and Morty",
            description = "Get random character of Rick and Morty")
    public CharacterOfRickyAndMortyDto getRandM() {
        return preparedResponseToController.getCharacterWithRandomId();
    }

    @GetMapping("/byname")
    @Operation(summary = "Get character with input name",
            description = "Get character with input name")
    public List<CharacterOfRickyAndMortyDto> getRandMByName(@RequestParam String name) {
        return preparedResponseToController.getCharacterByName(name);
    }
}
