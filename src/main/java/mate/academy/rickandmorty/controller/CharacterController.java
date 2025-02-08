package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.internal.cartoon.CartoonCharacterSearchDto;
import mate.academy.rickandmorty.services.CharacterService;
import mate.academy.rickandmorty.services.rickandmortyclient.CharacterDtoConvertService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character management", description = "Endpoints for managing cartoon characters")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterDtoConvertService convertService;

    @GetMapping("/all")
    @Operation(summary = "Show all characters",
            description = "Shows all the characters from the Rick and Morty universe.")
    public List<CartoonCharacterDto> findAll(Pageable pageable) {
        return characterService.findAll(pageable);
    }

    @GetMapping("/search")
    @Operation(summary = "Search by name",
            description = "Show all cartoon characters that contain this string")
    public List<CartoonCharacterDto> findAllByName(
            CartoonCharacterSearchDto cartoonCharacterSearchDto
    ) {
        return characterService.searchAllByName(cartoonCharacterSearchDto);
    }

    @GetMapping("/get")
    @Operation(summary = "Shows a cartoon character",
            description = "Shows the biography of a random cartoon character")
    public CartoonCharacterDto getRandomCharacter() {
        return characterService.randomCharacter();
    }

    @GetMapping("/request")
    public ResponseEntity<String> apiRequest(Pageable pageable) {
        convertService.getCharacterConvertedDto();
        return ResponseEntity.ok("Data was fetched and saved successfully");
    }
}
