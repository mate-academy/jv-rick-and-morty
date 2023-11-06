package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick And Morty characters",
        description = "Endpoints of Rick and Morty's characters "
                + "in DB of jv-rick-and-morty application")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/character")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Getting a character from data base by random id")
    public CharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Find all characters by name",
            description = "Getting a list of characters from Rick and Morty series "
                    + "from all characters data base")
    public List<CharacterDto> getCharactersByName(@PathVariable String name, Pageable pageable) {
        return characterService.searchCharacters(name, pageable);
    }
}
