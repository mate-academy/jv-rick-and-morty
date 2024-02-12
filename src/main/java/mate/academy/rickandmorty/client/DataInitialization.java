package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitialization {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final RickAndMortyApiClient rickAndMortyApiClient;

    @PostConstruct
    private void init() {
        List<Character> characterFromRickAndMorty =
                rickAndMortyApiClient.getAllCharacters().stream()
                        .map(characterMapper::toCharacterModel)
                        .toList();
        characterRepository.saveAll(characterFromRickAndMorty);
    }
}
