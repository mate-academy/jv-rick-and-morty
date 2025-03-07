package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        return characterRepository.findRandomCharacter()
                .orElseThrow(() -> new RuntimeException("Can`t get random character"));
    }

    @Override
    public List<Character> searchCharacterByName(String name) {
        return characterRepository.findByNameContaining(name);
    }
}
