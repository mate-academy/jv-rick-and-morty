package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharactersInitializer {
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void fetchCharactersToDb() {
        List<Character> characters = rickAndMortyClient.getCharacters().stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
