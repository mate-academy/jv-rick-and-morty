package com.parkhomovsky.rickandmorty.service;

import com.parkhomovsky.rickandmorty.dto.ApiCharacterDto;
import com.parkhomovsky.rickandmorty.dto.ApiResponseDto;
import com.parkhomovsky.rickandmorty.dto.CharacterResponseDto;
import com.parkhomovsky.rickandmorty.mapper.MovieCharacterMapper;
import com.parkhomovsky.rickandmorty.model.MovieCharacter;
import com.parkhomovsky.rickandmorty.repository.MovieCharacterRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final MovieCharacterMapper movieCharacterMapper;

    public MovieCharacterServiceImpl(HttpClient httpClient,
                                     MovieCharacterRepository movieCharacterRepository,
                                     MovieCharacterMapper movieCharacterMapper) {
        this.httpClient = httpClient;
        this.movieCharacterRepository = movieCharacterRepository;
        this.movieCharacterMapper = movieCharacterMapper;
    }

    @Override
    @PostConstruct
    @Scheduled(cron = "* */30 * * * ?")
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters was invoked at " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ApiResponseDto.class);
        saveDtosToDb(apiResponseDto);
        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient.get(apiResponseDto.getInfo().getNext(),
                    ApiResponseDto.class);
            saveDtosToDb(apiResponseDto);
        }
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = movieCharacterRepository.count();
        long randomCharacterId = new Random().nextLong(count);
        return movieCharacterMapper
                .toResponseDto(movieCharacterRepository.findById(randomCharacterId)
                .orElseThrow(() -> new RuntimeException("No such character")));
    }

    @Override
    public List<CharacterResponseDto> getAllByNamePart(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart).stream()
                .map(movieCharacterMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private void saveDtosToDb(ApiResponseDto responseDto) {
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(responseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();

        List<MovieCharacter> existingCharacters =
                movieCharacterRepository.findAllByExternalIdIn(externalIds);
        Map<Long, MovieCharacter> existingCharactersWithId = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingCharactersIds = existingCharactersWithId.keySet();

        externalIds.removeAll(existingCharactersIds);

        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(i -> movieCharacterMapper.parseApiCharacterResponseDto(externalDtos.get(i)))
                .collect(Collectors.toList());
        movieCharacterRepository.saveAll(charactersToSave);
    }
}
