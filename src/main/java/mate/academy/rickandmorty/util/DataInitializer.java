package mate.academy.rickandmorty.util;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CharactersClient charactersClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public DataInitializer(CharactersClient charactersClient,
            CharacterRepository characterRepository,
            CharacterMapper characterMapper) {
        this.charactersClient = charactersClient;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        List<CharacterResponseDataDto> externalCharacters = charactersClient.getAllCharacters();

        List<Character> charactersToSave = externalCharacters.stream()
                .map(characterMapper::toModel)
                .toList();

        characterRepository.saveAll(charactersToSave);
        System.out.println(charactersToSave.size() + " characters were saved to the database.");
    }
}
