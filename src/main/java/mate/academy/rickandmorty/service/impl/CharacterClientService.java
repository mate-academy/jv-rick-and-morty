package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClientService {
    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void saveCharactersToDb() {
        List<Character> characters = characterClient.getCharacters().stream()
                .map(characterMapper::toEntity)
                .collect(Collectors.toList());
        characterRepository.saveAll(characters);
    }
}
