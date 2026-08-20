package mate.academy.rickandmorty.client;

import mate.academy.rickandmorty.dto.RickAndMortyResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyClient {

    private static final String API_URL = "https://rickandmortyapi.com/api/character";

    private final RestTemplate restTemplate;

    public RickAndMortyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RickAndMortyResponseDto getCharacters(int page) {
        String url = API_URL + "?page=" + page;

        return restTemplate.getForObject(
                url,
                RickAndMortyResponseDto.class
        );
    }
}
