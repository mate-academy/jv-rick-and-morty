package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterInitializer {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @PostConstruct
    public void init() {
        List<Character> characters =
                characterClient.getAllCharacters().stream()
                        .map(characterMapper::toModel)
                        .toList();
        characterRepository.saveAll(characters);
    }
}
