package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.character.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public AppStartupRunner(CharacterClient characterClient,
                            CharacterMapper characterMapper,
                            CharacterRepository characterRepository) {
        this.characterClient = characterClient;
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        CharacterResponseDataDto characterResponseDataDto = characterClient.getAllCharacters();

        List<Character> characters = characterResponseDataDto.getResults()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
