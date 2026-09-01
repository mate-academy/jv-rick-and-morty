package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;
    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data synchronization from Rick & Morty API...");

        int page = 1;
        while (true) {
            CharacterResponseDto responseDto = rickAndMortyClient.fetchPage(page);

            if (responseDto == null || responseDto.getResults() == null) {
                break;
            }

            List<MovieCharacter> movieCharacters = responseDto.getResults().stream()
                    .map(characterMapper::toModel)
                    .toList(); //.co

            characterService.saveAll(movieCharacters);
            log.info("Successfully synchronized page: {}", page);
            page++;
        }
        log.info("Data synchronization completed successfully! All characters saved to local DB");
    }
}
