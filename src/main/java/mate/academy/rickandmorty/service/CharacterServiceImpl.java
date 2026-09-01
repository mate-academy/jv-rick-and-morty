package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public void saveAll(List<MovieCharacter> characters) {
        characterRepository.saveAll(characters);
    }

    @Override
    public List<MovieCharacter> findAllByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = characterRepository.count();
        if (count == 0) {
            throw new RuntimeException("No characters found in DB");
        }
        return characterRepository.getRandomCharacterNative();
    }
}
