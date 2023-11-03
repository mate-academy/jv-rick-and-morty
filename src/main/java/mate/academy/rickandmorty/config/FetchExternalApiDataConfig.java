package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class FetchExternalApiDataConfig {
    private static final int INITIAL_PAGE = 1;

    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterService characterService;

    @EventListener(ApplicationReadyEvent.class)
    public void fetchData() {
        ExternalCharacterDataDto currentData = rickAndMortyClient.getCharacterDataAt(INITIAL_PAGE);
        saveData(currentData.results());

        int currentPage = INITIAL_PAGE;
        do {
            currentPage++;
            currentData = rickAndMortyClient.getCharacterDataAt(currentPage);
            saveData(currentData.results());
        } while (currentData.info().next() != null);
    }

    private void saveData(List<ExternalCharacterDto> characterDtos) {
        characterService.saveAll(characterDtos);
    }
}
