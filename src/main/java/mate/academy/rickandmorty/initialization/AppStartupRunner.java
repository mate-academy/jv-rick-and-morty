package mate.academy.rickandmorty.initialization;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {
    private final CharacterRepository repository;

    private final CharacterClient characterClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (characterClient.getDataByPage(1).getInfo().getCount() != repository.count()) {
            List<Character> allCharacters = characterClient.getAllCharacters();
            for (Character character : allCharacters) {
                if (!repository.findByExternalId(character.getExternalId()).isPresent()) {
                    repository.save(character);
                }
            }
        }
        System.out.println(characterClient.getDataByPage(1));
    }
}
