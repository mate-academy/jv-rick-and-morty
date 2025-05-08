package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyResponseDataDto;
import mate.academy.rickandmorty.mapper.RickAndMortyCharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyCharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyDbCreator {

    private final RickAndMortyClient rickAndMortyClient;
    private final RickAndMortyCharacterMapper rickAndMortyCharacterMapper;
    private final RickAndMortyCharacterRepository rickAndMortyCharacterRepository;

    @PostConstruct
    public void init() {
        createRickAndMortyCharacters("https://rickandmortyapi.com/api/character");
    }

    public void createRickAndMortyCharacters(String baseUrl) {
        while (baseUrl != null) {
            RickAndMortyResponseDataDto response = rickAndMortyClient
                    .getRickAndMortyCharacter(baseUrl);
            response.getResults().stream()
                    .map(rickAndMortyCharacterMapper::toModel)
                    .forEach(rickAndMortyCharacterRepository::save);
            baseUrl = response.getInfo().getNext();
        }
    }
}
