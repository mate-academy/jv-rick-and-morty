package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.rickandmortyapi.RickAndMortyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {
    private final RickAndMortyService rickAndMortyService;

    @Override
    public void run(String... args) throws Exception {
        rickAndMortyService.saveAllCharacters();
    }
}
