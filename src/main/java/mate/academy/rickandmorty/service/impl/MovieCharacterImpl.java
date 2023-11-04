package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.mapper.MovieCharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCharacterImpl implements MovieCharacterService {
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper movieCharacterMapper;

    @Override
    public MovieCharacterResponseDto getRandom() {
        MovieCharacter randomMovieCharacter = movieCharacterRepository.findRandom()
                .orElseThrow(() -> new NoSuchElementException("Can't find random character in DB"));
        return movieCharacterMapper.toDto(randomMovieCharacter);
    }

    @Override
    public List<MovieCharacterResponseDto> findByNameLike(String namepart) {
        return movieCharacterRepository.getMovieCharacterByNameContainsIgnoreCase(namepart)
                .stream()
                .map(movieCharacterMapper::toDto)
                .toList();
    }
}
