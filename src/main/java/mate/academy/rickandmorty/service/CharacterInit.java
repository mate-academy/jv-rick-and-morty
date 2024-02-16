package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterInit {
    private final RickAndMortyClient client;
    private final CharacterService characterService;

    @PostConstruct
    public void initCharacters() {
        CharacterResponseDataDto characters = client.getCharacters();
        characterService.saveAll(characters.results());
    }
}
