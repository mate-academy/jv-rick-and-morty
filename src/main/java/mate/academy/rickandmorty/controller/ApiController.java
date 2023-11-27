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

    @GetMapping("/fetch-data")
    public List<CharacterEntity> fetchData() throws IOException {
        String jsonResponse = apiService.fetchCharacters();
        return apiService.parseJsonString(jsonResponse);
    }

    @GetMapping("/characters")
    public List<CharacterEntity> getCharactersByName(@RequestParam String name) {
        return characterService.findByNameContaining(name);
    }
}
