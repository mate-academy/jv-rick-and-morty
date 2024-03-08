package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RickAndMortyThirdPartyApiController {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final RickAndMortyClient rickAndMortyClient;
    private final RickAndMortyService rickAndMortyService;

    @GetMapping
    public void test() {
        rickAndMortyClient.getCharacters(CHARACTER_URL)
                .forEach(t -> t.getRickAndMortyDataResponseDtoList()
                        .forEach(rickAndMortyService::save));
    }
}
