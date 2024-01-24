package mate.academy.rickandmorty;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromExternalApiDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterSaverToDataBase;
import mate.academy.rickandmorty.service.CharactersApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private final CharactersApiClient charactersApiClient;
    private final CharacterSaverToDataBase characterSaverToDataBase;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @PostConstruct
    public void init() {
        List<CharacterFromExternalApiDto> allCharactersFromApi =
                charactersApiClient.getAllCharactersFromApi();

        List<Character> characters = characterSaverToDataBase
                .convertListOfExternalDtoToModel(allCharactersFromApi);

        characterSaverToDataBase.saveCharacterModelListToDataBase(characters);
    }
}
