package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieCharacterDataInitializer {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final MovieCharacterHttpClient movieCharacterClient;
    private final MovieCharacterService movieCharacterService;

    @PostConstruct
    private void loadData() {
        ApiResponseDto charactersInfo = movieCharacterClient.getCharacters(BASE_URL);
        while (charactersInfo.getInfo().getNext() != null) {
            movieCharacterService.saveAll(charactersInfo.getResults().stream().toList());
            charactersInfo = movieCharacterClient.getCharacters(charactersInfo.getInfo().getNext());
        }
        movieCharacterService.saveAll(charactersInfo.getResults().stream().toList());
    }
}
