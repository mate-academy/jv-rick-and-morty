package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "https://rickandmortyapi.com/api")
public interface RickyMortyClient {
    @GetMapping("/character")
    List<Character> getCharacters();
}
