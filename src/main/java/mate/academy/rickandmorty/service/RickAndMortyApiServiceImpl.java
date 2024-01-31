package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {
    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        return characterRepository.getRandomCharacter();
    }

    @Override
    public List<Character> findCharacterBySearchString(String searchString) {
        return characterRepository.findCharacterBySearchString(searchString);
    }
}
