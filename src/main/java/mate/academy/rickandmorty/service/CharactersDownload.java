package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharactersDownload {
    private final CharacterService characterService;
    private final RickAndMortyClient client;

    @PostConstruct
    public void downloadData() {
        List<CharacterExternalResponseDto> fetchedCharacters = client.getCharacters();
        characterService.saveAll(fetchedCharacters);
    }
}
