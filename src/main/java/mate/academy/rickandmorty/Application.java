package mate.academy.rickandmorty;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.CharactersApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final CharactersApiClient charactersApiClient;
    private final CharacterService characterService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @PostConstruct
    public void init() {
        List<CharacterFromExternalApiDto> allCharactersFromApi =
                charactersApiClient.getAllCharactersFromApi();

        characterService.saveCharacters(allCharactersFromApi);
    }
}
