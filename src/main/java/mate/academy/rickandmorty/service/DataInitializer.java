package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataInitializer {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public DataInitializer(RickAndMortyClient client,
                           CharacterRepository characterRepository,
                           CharacterMapper characterMapper) {
        this.client = client;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        if (characterRepository.count() > 0) {
            return;
        }
        if (characterRepository.count() == 0) {
            List<Character> characters = client.fetchAllCharacters().stream()
                    .map(characterMapper::toModel)
                    .toList();
            characterRepository.saveAll(characters);
        }
    }
}

