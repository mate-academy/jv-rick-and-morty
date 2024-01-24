package mate.academy.rickandmorty.client;

import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rickandmortyapi", url = "${client.rickandmortyapi.host}")
public interface CharacterClient {

    @GetMapping(value = "/character",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CharacterResponseDataDto getAllCharacters();

}
