package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RickAndMortyClient rickAndMortyClient;

    @GetMapping("/characters")
    public CharacterEntity getRandomCharacter() {
        return rickAndMortyClient.makeRequestRandomCharacter();
    }
}
