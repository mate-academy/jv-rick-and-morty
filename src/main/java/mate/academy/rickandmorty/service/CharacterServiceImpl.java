package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        int randomPageNumber = ThreadLocalRandom.current().nextInt((int)count);
        Page<MovieCharacter> characterPage = characterRepository
                .findAll(PageRequest.of(randomPageNumber, 1)
        );
        return characterPage.getContent().get(0);
    }
}
