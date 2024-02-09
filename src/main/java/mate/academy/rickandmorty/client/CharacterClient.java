package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {

    private final CharacterInitializer characterInitializer;

    @PostConstruct
    private void init() {
        characterInitializer.initializeCharacters();
    }
}
