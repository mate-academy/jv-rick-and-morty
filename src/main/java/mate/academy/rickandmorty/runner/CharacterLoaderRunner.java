package mate.academy.rickandmorty.runner;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.exception.LoadExternalDataException;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CharacterLoaderRunner {
    private final CharacterClient client;
    private final CharacterRepository repository;

    @Bean
    public ApplicationRunner loadCharactersAtStartUp() {
        return args -> {
            try {
                repository.saveAll(client.getAll());
            } catch (Exception e) {
                throw new LoadExternalDataException(
                        "Failed to load characters from external DB", e);
            }
        };
    }
}
