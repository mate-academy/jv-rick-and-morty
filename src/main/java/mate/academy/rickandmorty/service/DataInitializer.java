package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public DataInitializer(RickAndMortyClient client,
                           CharacterRepository characterRepository,
                           CharacterMapper characterMapper) {
        this.client = client;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public void run(String... args) {
        if (characterRepository.count() > 0) {
            return;
        }
        List<ExternalCharacterDto> externalDtos = client.fetchAllCharacters();
        List<Character> characters = externalDtos.stream()
                .map(characterMapper::toModel)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
    }
}

