package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.AnimationCharacterResponseDto;
import mate.academy.rickandmorty.service.AnimationCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book store management", description = "Endpoints for management book store")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rickandmorty")
public class AnimationCharacterController {
    private final AnimationCharacterService animationCharacterService;

    @Operation(summary = "Get list of characters",
            description = "Get list characters by the search string")
    @GetMapping
    public List<AnimationCharacterResponseDto> getAllBySearchString(String search) {
        return animationCharacterService.findAllBySearchString(search);
    }

    @Operation(summary = "Get random character", description = "Get random character")
    @GetMapping("/random")
    public AnimationCharacterResponseDto getRandomCharacter() {
        return animationCharacterService.getRandomCharacter();
    }
}
