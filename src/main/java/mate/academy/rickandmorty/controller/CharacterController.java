package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty characters Wiki")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterController {
    private static final Long ONE_NUMBER = 1L;
    private final CharacterService characterService;
    private final Random random;

    @GetMapping("/random")
    @Operation(summary = "Get a random character from Rick and Morty universe")
    public CharacterDto getRandom() {
        Long count = characterService.count();
        return characterService.findById(random.nextLong(ONE_NUMBER, count + ONE_NUMBER));
    }

    @GetMapping
    @Operation(summary = "Get list of characters from Rick and Morty universe by part of a name")
    public List<CharacterDto> getAllByName(@RequestParam String string) {
        return characterService.getAllByName(string);
    }
}
