package mate.academy.rickandmorty.config;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.RickandmortyapiClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final CharacterRepository repository;
    private final RickandmortyapiClient client;
    private final CharacterMapper mapper;

    @Bean
    public ApplicationRunner fetchDataToDB() {
        return args -> {
            repository.saveAll(client.getCharacters().stream()
                    .map(mapper::toModel)
                    .toList());
        };
    }
}
