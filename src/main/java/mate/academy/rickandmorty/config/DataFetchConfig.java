package mate.academy.rickandmorty.config;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Configuration
public class DataFetchConfig {
    private static final Long firstPage = 1L;
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterService characterService;

    @EventListener(ApplicationReadyEvent.class)
    public void fetchData() {
        List<ExternalCharacterDto> externalCharacterDtos = new ArrayList<>();

        long currentPage = firstPage;
        CharacterResponseDataDto charactersData;
        do {
            charactersData = rickAndMortyClient.getCharactersData(currentPage);
            externalCharacterDtos.addAll(charactersData.results());
            currentPage++;
        } while (currentPage <= charactersData.info().pages());

        characterService.saveAll(externalCharacterDtos);
    }
}
