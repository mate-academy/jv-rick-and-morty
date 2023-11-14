package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CartoonCharacterDto;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Characters catalog", description = "Endpoint for getting Rick and Morty characters")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/characters")
public class CartoonCharacterController {
    private final CartoonCharacterService cartoonCharacterService;

    @Operation(summary = "Get random character",
            description = "Get random character from DB")
    @GetMapping(value = "/random")
    public CartoonCharacterDto getRandom() {
        return cartoonCharacterService.getRandom();
    }

    @Operation(summary = "Get characters by name",
            description = "Get characters list by name or part thereof")
    @GetMapping
    public List<CartoonCharacterDto> findByName(@RequestParam String name, Pageable pageable) {
        return cartoonCharacterService.findByName(name, pageable);
    }
}
