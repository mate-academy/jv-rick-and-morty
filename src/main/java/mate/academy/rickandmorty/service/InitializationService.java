package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitializationService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;

    @PostConstruct
    public void init() {
        characterRepository.saveAll(characterClient.getAllCharacters());
    }
}
