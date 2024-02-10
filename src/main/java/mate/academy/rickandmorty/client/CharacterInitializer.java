package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterInitializer {
    private final CharacterRepository characterRepository;

    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    private void init() {
        characterRepository.saveAll(
                characterMapper.toModels(characterClient.getAllHeroFromApi())
        );
    }
}
