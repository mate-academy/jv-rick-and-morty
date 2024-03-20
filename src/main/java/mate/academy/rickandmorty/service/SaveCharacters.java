package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SaveCharacters {
    private final CharacterService characterService;
    private final RickAndMortyClient rickAndMortyClient;

    @PostConstruct
    public void saveCharacters() {
        List<CharacterResponseDataDto> characterResponseDataDto =
                rickAndMortyClient.getCharacters();
        characterService.saveAll(characterResponseDataDto);
    }
}
