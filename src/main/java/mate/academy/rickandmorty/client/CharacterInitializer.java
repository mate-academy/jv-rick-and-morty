package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterInitializer {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final CharacterClient client;

    @PostConstruct
    private void init() {
        characterRepository.saveAll(client.getAllCharacters().stream()
                .map(characterMapper::toModel)
                .toList());
    }
}
