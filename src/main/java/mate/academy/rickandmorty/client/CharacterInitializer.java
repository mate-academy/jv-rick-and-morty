package mate.academy.rickandmorty.client;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromApiDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterInitializer {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @PostConstruct
    public void init() {
        List<CharacterFromApiDto> allCharacters = characterClient.getAllCharacters();
        characterRepository.saveAll(
                allCharacters.stream()
                        .map(characterMapper::toModel)
                        .toList()
        );
    }
}
