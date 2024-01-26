package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.MovieCharacterMapper;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private static final String STARTING_URL = "https://rickandmortyapi.com/api/character";
    private final MovieCharacterClient movieCharacterClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper movieCharacterMapper;
    private Long randomId;

    @PostConstruct
    public void init() {
        saveAllMovieCharacters();
    }

    @Override
    public void saveAllMovieCharacters() {
        String currentUrl = STARTING_URL;
        while (currentUrl != null) {
            ApiResponseDto responseDto = movieCharacterClient.getResponseDto(currentUrl);
            responseDto.getResults().stream()
                    .map(movieCharacterMapper::toModel)
                    .forEach(movieCharacterRepository::save);
            currentUrl = responseDto.getInfo().getNext();
        }
    }

    @Override
    public MovieCharacterDto getRandomCharacter() {
        randomId = new Random().nextLong(movieCharacterRepository.count() + 1);
        return movieCharacterRepository.findById(randomId)
                .map(movieCharacterMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Something went wrong. Try again!")
        );
    }

    @Override
    public List<MovieCharacterDto> findAllCharactersByName(String name) {
        return movieCharacterRepository.findAllByNameContainsIgnoreCase(name).stream()
                .map(movieCharacterMapper::toDto)
                .toList();
    }
}
