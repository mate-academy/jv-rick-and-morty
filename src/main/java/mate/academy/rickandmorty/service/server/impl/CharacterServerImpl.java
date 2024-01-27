package mate.academy.rickandmorty.service.server.impl;

import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.character.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.client.CharacterClient;
import mate.academy.rickandmorty.service.random.RandomService;
import mate.academy.rickandmorty.service.server.CharacterServer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServerImpl implements CharacterServer {
    private static final int PAGE_AMOUNT = 42;
    private static final int MIN_CHARACTER_ID = 1;
    private static final int MAX_CHARACTER_ID = 826;
    private final CharacterRepository characterRepository;
    private final RandomService randomService;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAllFromExternalApi() throws IOException, InterruptedException {
        for (int i = 1; i <= PAGE_AMOUNT; i++) {
            for (CharacterDto characterDto : characterClient.getCharactersByPage(i).getResults()) {
                characterRepository.save(characterMapper.toModel(characterDto));
            }
        }
    }

    @Override
    public List<Character> findByName(String name) {
        List<Character> characters = characterRepository.findByName(name);
        if (characters.isEmpty()) {
            throw new EntityNotFoundException("Cannot find any character by name: " + name);
        }
        return characters;
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cannot find Character By id: " + id));
    }

    @Override
    public Character getRandomCharacter() {
        int id = randomService.getRandomIntNumber(MIN_CHARACTER_ID, MAX_CHARACTER_ID);
        return characterRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Cannot find Character By id: " + id));

    }
}
