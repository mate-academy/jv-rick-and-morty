package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final MovieCharacterRepository movieCharacterRepository;

    @Override
    public MovieCharacter getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);
        Optional<MovieCharacter> randomCharacter = movieCharacterRepository.findById(randomId);
        return randomCharacter.orElseThrow();
    }

    @Override
    public List<MovieCharacter> findAllByNameContainsIgnoreCase(String name) {
        return movieCharacterRepository.findAllByNameContainsIgnoreCase(name);
    }
}
