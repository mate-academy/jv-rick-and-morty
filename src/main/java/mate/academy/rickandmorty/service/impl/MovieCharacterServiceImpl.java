package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.MovieCharacterMapper;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import mate.academy.rickandmorty.service.MovieCharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper movieCharacterMapper;

    @Override
    public void saveAll(List<ApiCharacterDto> characterDtos) {
        movieCharacterRepository.saveAll(characterDtos.stream()
                .map(movieCharacterMapper::toModel)
                .toList());
    }

    @Override
    public MovieCharacterResponseDto getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomId = (long) (Math.random() * count);
        return movieCharacterMapper.toResponseDto(
                movieCharacterRepository.findById(randomId).orElseThrow(
                        () -> new EntityNotFoundException("Can't find the entity by id " + count)));
    }

    @Override
    public List<MovieCharacterResponseDto> findAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContainsIgnoreCase(namePart).stream()
                .map(movieCharacterMapper::toResponseDto)
                .toList();
    }
}
