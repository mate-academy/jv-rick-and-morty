package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.RickAndMortyApiDataService;
import mate.academy.rickandmorty.service.RickAndMortyApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final RickAndMortyApiService rickAndMortyApiService;

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    Character getRandomCharacter() {
        return rickAndMortyApiService.getRandomCharacter();
    }

    @GetMapping("/name/{searchString}")
    @ResponseStatus(HttpStatus.OK)
    List<Character> findCharacterBySearchString(@PathVariable String searchString) {
        return rickAndMortyApiService.findCharacterBySearchString(searchString);
    }
}
