package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new character", description = "Create new character by random id")
    public CharacterDto postCharacter() {
        return characterService.postRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Get list of characters", description = "Get list of characters by name")
    public List<CharacterDto> searchCharacters(@RequestParam String name) {
        return characterService.searchByString(name);
    }
}
