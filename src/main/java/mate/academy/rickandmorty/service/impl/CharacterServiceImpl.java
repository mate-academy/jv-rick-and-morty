package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterWikiDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.client.CharacterClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterWikiDto generateRandomCharacter() {
        return characterMapper.toDto(characterRepository.findById(generateRandomId()).orElseThrow(
                () -> new RuntimeException("Can't generate random character")));
    }

    private int generateRandomId() {
        int elementsAmount = characterRepository.findAll().size();
        return new Random().nextInt(1, elementsAmount);
    }

    @PostConstruct
    public void retrieveAndSaveCharactersToDB() {
        List<Character> characters = characterClient.getAllCharactersData().getResults().stream()
                .map(characterMapper::toModel)
                .toList();

        characterRepository.saveAll(characters);
    }
}
