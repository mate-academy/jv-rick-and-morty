package mate.academy.rickandmorty.config;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.impl.ApiCharactersClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final CharacterRepository characterRepository;

    private final ApiCharactersClient charactersClient;

    private final CharacterMapper characterMapper;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        Set<String> existingIds = characterRepository.findAllExternalIds();

        List<Character> characters = charactersClient.fetchCharactersFromApi().stream()
                .map(characterMapper::toModelFromApiResponse)
                .filter(character -> !existingIds.contains(character.getExternalId()))
                .toList();

        characterRepository.saveAll(characters);
    }
}
