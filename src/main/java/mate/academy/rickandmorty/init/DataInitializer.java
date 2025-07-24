package mate.academy.rickandmorty.init;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.ExternalApiClient;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CharacterRepository characterRepository;
    private final ExternalApiClient externalApiClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        if (characterRepository.count() == 0) {
            List<CharacterEntity> characters = externalApiClient.fetchAllCharacters().stream()
                    .map(characterMapper::toEntity)
                    .toList();
            characterRepository.saveAll(characters);
        }
    }
}
