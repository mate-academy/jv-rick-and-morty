package mate.academy.rickandmorty.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> getCharactersByName(String name) {
        return characterRepository.getCharactersByName(name);
    }

    @Override
    public Character getRandomCharacter() {
        Long id = Long.valueOf(new Random().nextInt(826));
        return characterRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(
                        "Can't get character by id " + id));
    }
}
