package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization {
    private final CharacterService characterService;
    private final RickyMortyClient rickyMortyClient;

    @PostConstruct
    public void loadDataToDb() {
        List<Character> characters = rickyMortyClient.getCharacters();
        characterService.saveAll(characters);
    }
}
