package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterDto;
import mate.academy.rickandmorty.service.external.RickAndMortyExternalApiClient;
import mate.academy.rickandmorty.service.internal.CartoonCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty manger",
        description = "Endpoints for Rick and Morty cartoon character API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CartoonCharacterController {
    private final CartoonCharacterService cartoonCharacterService;
    private final RickAndMortyExternalApiClient rickAndMortyExternalApiClient;

    @Operation(summary = "getting random character",
            description = "getting random Rick and Morty cartoon character")
    @GetMapping("/random")
    public CartoonCharacterDto getRandomCharacter() {
        rickAndMortyExternalApiClient.uploadDataBase();
        return cartoonCharacterService.getRandomCartoonCharacter();
    }

    @Operation(summary = "getting character is like",
            description = "getting Rick and Morty cartoon character"
                    + "which names contain searched string")
    @GetMapping("/islike/{string}")
    public List<CartoonCharacterDto> getRandomCharacter(@PathVariable String string) {
        rickAndMortyExternalApiClient.uploadDataBase();
        return cartoonCharacterService.findNamesWhichContainString(string);
    }
}
