package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.SeriesCharacterDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyCharacterFetcherImpl implements RickAndMortyCharacterFetcher {
    private final RickAndMortyClient rickAndMortyClient;
    private final SeriesCharacterService seriesCharacterService;

    @Override
    @PostConstruct
    public void fetchRickAndMortyCharacters() {
        int characterPageCount = rickAndMortyClient.getCharacterPageCount();
        for (int i = 1; i <= characterPageCount; i++) {
            List<SeriesCharacterDto> characters = rickAndMortyClient.getCharacters(i);
            seriesCharacterService.saveAll(characters);
        }
    }
}
