package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyApiResponseDataDto;
import mate.academy.rickandmorty.service.character.CharacterService;
import mate.academy.rickandmorty.service.client.Client;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RickAndMortyService {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private Client<RickAndMortyApiResponseDataDto> client;
    private CharacterService characterService;

    @PostConstruct
    private void init() {
        client.setUrl(API_URL);
        while (client.getUrl() != null) {
            RickAndMortyApiResponseDataDto dataFromApi = client
                    .getDataFromApi(RickAndMortyApiResponseDataDto.class);
            client.setUrl(dataFromApi.info().next());
            characterService.addListCharacter(dataFromApi.results());
        }
        System.out.println("The database is successfully updated...");
    }
}
