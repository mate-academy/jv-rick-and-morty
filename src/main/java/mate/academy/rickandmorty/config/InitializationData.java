package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.entity.Character;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import mate.academy.rickandmorty.service.impl.ExternalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializationData implements CommandLineRunner {
    private final ExternalClient externalClient;
    private final RickAndMortyRepository rickAndMortyRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Character> allCharacters = externalClient.getAllCharacters();
        rickAndMortyRepository.saveAll(allCharacters);
    }
}
