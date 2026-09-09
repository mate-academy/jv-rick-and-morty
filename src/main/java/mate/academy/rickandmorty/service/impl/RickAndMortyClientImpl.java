package mate.academy.rickandmorty.service.impl;

import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.dto.RickAndMortyPageDto;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyClientImpl implements RickAndMortyClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public RickAndMortyClientImpl(
            RestTemplate restTemplate,
            @Value("${rick-and-morty.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<RickAndMortyCharacterDto> getAllCharacters() {
        List<RickAndMortyCharacterDto> characters = new ArrayList<>();
        String nextPageUrl = baseUrl;

        while (nextPageUrl != null) {
            RickAndMortyPageDto page = restTemplate.getForObject(
                    nextPageUrl,
                    RickAndMortyPageDto.class);

            if (page == null || page.info() == null || page.results() == null) {
                throw new IllegalStateException("Rick and Morty API returned an invalid response");
            }

            characters.addAll(page.results());
            nextPageUrl = page.info().next();
        }
        return characters;
    }
}
