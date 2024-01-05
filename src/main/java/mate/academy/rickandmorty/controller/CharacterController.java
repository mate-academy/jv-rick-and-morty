package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Rick and Morty management",
        description = "Endpoints for managing Rick and Morty characters")
public class CharacterController {
    private final CharacterService service;

    @GetMapping("/random")
    @Operation(summary = "Get random Rick and Morty character")
    public CharacterDto getRandom() {
        return service.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search Rick and Morty character",
            description = """
                    Сase-insensitive search for Rick and Morty characters by name occurrence,
                    default sort by id""")
    public List<CharacterDto> searchByName(@RequestParam String name,
                                           @ParameterObject @PageableDefault Pageable pageable) {
        return service.findAllByName(name, pageable);
    }
}
