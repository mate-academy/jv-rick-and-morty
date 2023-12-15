package mate.academy.rickandmorty.db_populate;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyApiClient;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseContent {
    private final RickAndMortyApiClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    private void postConstruct() {
        List<CharacterFromRickAndMorty> characterFromRickAndMorty = client.getAllCharacters().stream()
                .map(characterMapper::toCharacterModel)
                .toList();
        characterRepository.saveAll(characterFromRickAndMorty);
    }
}
