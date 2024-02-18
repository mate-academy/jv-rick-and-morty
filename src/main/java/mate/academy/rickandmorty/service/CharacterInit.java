package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterInit {
    private final RickAndMortyClient client;
    private final CharacterService characterService;

    @PostConstruct
    public void initCharacters() {
        List<CharacterExternalResponseDto> characters = client.getCharacters();
        characterService.saveAll(characters);
    }
}
