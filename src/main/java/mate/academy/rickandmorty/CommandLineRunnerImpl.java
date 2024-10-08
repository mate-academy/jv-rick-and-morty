package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResultDto;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CharacterClient characterClient;
    private final RickAndMortyService rickAndMortyService;

    public CommandLineRunnerImpl(CharacterClient characterClient,
                                 RickAndMortyService rickAndMortyService) {
        this.characterClient = characterClient;
        this.rickAndMortyService = rickAndMortyService;
    }

    @Override
    public void run(String... args) {
        List<CharacterResultDto> allCharacters = characterClient.getAllCharacters();
        rickAndMortyService.saveAll(allCharacters);
    }
}
