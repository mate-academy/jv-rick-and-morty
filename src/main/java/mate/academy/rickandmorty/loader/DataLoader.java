package mate.academy.rickandmorty.loader;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader implements CommandLineRunner {

    private final CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {
        characterService.importCharacters();
    }
}
