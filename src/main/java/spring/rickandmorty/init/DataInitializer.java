package spring.rickandmorty.init;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.rickandmorty.client.RickAndMortyApiClient;
import spring.rickandmorty.dto.ExternalCharacterDto;
import spring.rickandmorty.mapper.CharacterMapper;
import spring.rickandmorty.model.Character;
import spring.rickandmorty.repository.CharacterRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RickAndMortyApiClient client;
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            List<Character> characters = client.getAllCharacters().stream()
                    .map(this::mapToCharacter) // <-- Викликаємо приватний метод
                    .toList();
            repository.saveAll(characters);
        }
    }

    private Character mapToCharacter(ExternalCharacterDto dto) {
        Character character = mapper.toEntity(dto);
        character.setExternalId(dto.getId().toString());
        return character;
    }
}
