package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
@Tag(name = "Characters API")
public class CharacterController {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @Operation(summary = "Losowa postać z bazy danych")
    @GetMapping("/random")
    public CharacterEntity random() {
        return service.random();
    }

    @Operation(summary = "Wyszukiwanie postaci po nazwie")
    @GetMapping("/search")
    public List<CharacterEntity> search(@RequestParam String name) {
        return service.search(name);
    }
}
