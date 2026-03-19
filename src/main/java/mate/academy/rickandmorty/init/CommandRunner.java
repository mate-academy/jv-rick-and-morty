package mate.academy.rickandmorty.init;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterApiMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private final CharacterClient characterClient;
    private final CharacterService characterService;
    private final CharacterApiMapper characterApiMapper;

    @Override
    public void run(String... args) throws Exception {
        if (characterService.count() > 0) {
            return;
        }
        List<Character> characters = characterClient.getAllCharacters().stream()
                .map(characterApiMapper::toEntity)
                .toList();
        characterService.saveAllCharacters(characters);
    }
}
