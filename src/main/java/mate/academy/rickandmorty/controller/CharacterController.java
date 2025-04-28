package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RnMCharacterDto;
import mate.academy.rickandmorty.dto.search.CharacterSearchParameters;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty characters wiki documentation", description = "This controllers give"
        + " you the opportunity to get some data about the characters of 'Rick and Morty' "
        + "universe")
public class CharacterController {
    private final CharacterService characterService;

    @Operation(summary = "Get a random character info", description = "Using this controller "
            + "without any arguments will give you information about randomly selected character")
    @GetMapping
    public RnMCharacterDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(summary = "Search characters by name part", description = "If you know only some "
            + "part of characters name but sure you will recognize it from some set of results -"
            + "this controller is for you. Give the part of characters name and you will get "
            + "paginated result with ability of sorting records")
    @GetMapping("/search")
    public Page<RnMCharacterDto> search(CharacterSearchParameters requestParams,
                                        Pageable pageable) {
        return characterService.search(requestParams, pageable);
    }
}
