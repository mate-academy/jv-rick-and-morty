package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.RickAndMortyWiki;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "\"Rick and Morty\" characters",
        description = "Endpoints to find some information about characters"
                + " in the universe of \"Rick and Morty\" series"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {
    private final RickAndMortyWiki rickAndMortyWiki;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Shows information about random character")
    public CharacterResponseDto getRandomCharacter() {
        return rickAndMortyWiki.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Find character by name",
            description = "Shows information about all characters with corresponding name")
    public List<CharacterResponseDto> findAllByName(@RequestParam String name, Pageable pageable) {
        return rickAndMortyWiki.findAllByName(name, pageable);
    }
}
