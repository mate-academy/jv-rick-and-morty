package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        return characterRepository.getRandomCharacter();
    }

    @Override
    public List<Character> getCharactersBySearchString(String searchString) {
        return characterRepository.findCharactersBySearchString(searchString);
    }
}
