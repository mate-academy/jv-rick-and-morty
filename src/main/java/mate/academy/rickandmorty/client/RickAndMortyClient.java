package mate.academy.rickandmorty.client;

import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "characters", url = "${rickandmorty.api.url}")
public interface RickAndMortyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/character?page={page}")
    CharacterResponseDataDto getCharactersData(@PathVariable Long page);
}
