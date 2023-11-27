package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.CharacterClient;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;

    @Override
    public List<Character> getCharactersByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Character getRandomCharacter() {
        Long id = Long.valueOf(new Random()
                .nextInt(characterClient.getInfoAboutApi().getInfo().getCount()));
        return characterRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(
                        "Can't get character by id " + id));
    }
}
