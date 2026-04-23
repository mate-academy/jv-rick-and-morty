package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final CharacterRepository characterRepository;

    private final CharactersClient charactersClient;

    private final CharacterMapper characterMapper;

    @Override
    public void run(ApplicationArguments args) {
        List<Character> characters = charactersClient.fetchCharactersFromApi().stream()
                .map(characterMapper::toModelFromApiResponse)
                .toList();

        characterRepository.saveAll(characters);
    }
}
