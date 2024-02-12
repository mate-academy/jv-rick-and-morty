package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters management", description = "Endpoints to managing character")
@RestController
@RequestMapping(value = "/characters")
@RequiredArgsConstructor
public class CharactersController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random Character",
            description = "Get one random Character from DB")
    public CharacterDto getAll() {
        return characterService.getRandom();
    }

    @GetMapping("/by_name")
    @Operation(summary = "Get Character by name",
            description = "Get Character by name like")
    public List<CharacterDto> getByName(@RequestParam String name) {
        return characterService.getListCharactersByName(name);
    }
}
