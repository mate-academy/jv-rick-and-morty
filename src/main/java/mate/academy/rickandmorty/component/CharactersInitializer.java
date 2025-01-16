package mate.academy.rickandmorty.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterInitializerService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersInitializer {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterInitializerService characterService;

    @PostConstruct
    public void init() {
        characterService.initializeCharacters(BASE_URL);
    }
}
