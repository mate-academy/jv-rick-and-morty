package mate.academy.rickandmorty.service.rick.and.morty.client;

import mate.academy.rickandmorty.dto.character.dtos.CharactersResponseDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rickAndMortyApi", url = "https://rickandmortyapi.com")
public interface RickAndMortyClient {
    @GetMapping("/api/character")
    CharactersResponseDataDto getCharacters(@RequestParam("page") int page);

    @GetMapping("/api/character")
    CharactersResponseDataDto getCharacters();
}
