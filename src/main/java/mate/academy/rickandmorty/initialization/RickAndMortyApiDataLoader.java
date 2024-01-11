package mate.academy.rickandmorty.initialization;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyApiClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyApiDataLoader {
    private static final String BASE_URL =
            "https://rickandmortyapi.com/api/character";

    private final CharacterService characterService;
    private final RickAndMortyApiClient apiClient;

    @PostConstruct
    private void loadData() {
        CharacterResponseDataDto charactersInfo = apiClient.getCharactersInfo(BASE_URL);

        while (charactersInfo.getInfo().next() != null) {
            characterService.saveAll(charactersInfo.getResults().stream().toList());
            charactersInfo = apiClient.getCharactersInfo(charactersInfo.getInfo().next());
        }
        characterService.saveAll(charactersInfo.getResults().stream().toList());
    }
}
