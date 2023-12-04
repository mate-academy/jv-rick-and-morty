package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService service;  
        
    @GetMapping("/random")
    @Operation(summary = "Get random character", 
            description = "Get character by rabdomly generacted id")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping
    @Operation(summary = "Get all characters with name containing request", 
            description = "Return list of all characters whose name contains the search string")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDto> getByName(@RequestParam String name) {
        return service.getByName(name);
    }
}
