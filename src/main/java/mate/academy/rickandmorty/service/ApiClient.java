package mate.academy.rickandmorty.service;

import java.util.List;

import mate.academy.rickandmorty.dto.CharacterGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "rickAndMortyApiClient", url = "https://rickandmortyapi.com/api")
public interface ApiClient {

    @GetMapping("/character")
    List<CharacterGetDto> getCharacters();
}