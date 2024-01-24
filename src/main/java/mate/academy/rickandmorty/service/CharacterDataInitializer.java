package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterDataInitializer {

    private final CharacterService characterService;

    @PostConstruct
    public void initializeCharacterData() {
        characterService.parseAndSave();
    }

}
