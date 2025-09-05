package mate.academy.rickandmorty.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CharacterService characterService;

    @PostConstruct
    public void init() {
        characterService.loadAllCharactersOnce();
    }
}

