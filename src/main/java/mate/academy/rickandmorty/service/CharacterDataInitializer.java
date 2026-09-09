package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "rick-and-morty.import.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class CharacterDataInitializer implements ApplicationRunner {
    private final RickAndMortyClient client;
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        if (repository.count() > 0) {
            return;
        }
        List<Character> characters = client.getAllCharacters().stream()
                .map(mapper::toModel)
                .toList();
        repository.saveAll(characters);
    }
}
