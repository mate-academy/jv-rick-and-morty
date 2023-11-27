package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final CharacterService characterService;

    @GetMapping("/characters")
    public List<CharacterEntity> getCharactersByName(@RequestParam String name) {
        return characterService.findByNameContaining(name);
    }

    @GetMapping("/external-id")
    public CharacterEntity getCharactersByExternalId(@RequestParam String externalId) {
        return characterService.findByExternalId(externalId);
    }
}
