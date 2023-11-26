package mate.academy.rickandmorty.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.ApiService;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;
    private final CharacterServiceImpl characterService;

    @GetMapping
    public List<CharacterEntity> fetchData(@RequestParam String name) throws IOException {
        String jsonResponse = apiService.fetchCharacters();
        List<CharacterEntity> characters = apiService.parseJsonString(jsonResponse);

        characterService.saveAll(characters);
        return characterService.findByNameContaining(name);
    }
}
