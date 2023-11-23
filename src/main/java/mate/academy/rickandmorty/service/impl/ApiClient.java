package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.MovieCharacterDataDto;
import mate.academy.rickandmorty.dto.internal.MovieCharacterResponseDto;
import mate.academy.rickandmorty.exception.FetchDataFailedException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.CharactersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApiClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharactersRepository charactersRepository;
    private final CharacterMapper characterMapper;
    private final RestTemplate restTemplate;

    @PostConstruct
    private void saveAllToDb() {
        String apiUrl = BASE_URL;

        while (apiUrl != null) {
            MovieCharacterDataDto dataDto = fetchData(apiUrl);
            processApiResponse(dataDto);
            apiUrl = dataDto.getInfo().getNext();
        }
    }

    public MovieCharacterResponseDto getRandomCharacter() {
        List<MovieCharacterResponseDto> results = charactersRepository.findAll().stream()
                .map(characterMapper::toDto)
                .toList();
        if (!results.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(results.size());
            return results.get(randomIndex);
        } else {
            throw new EntityNotFoundException("No movie characters found in DB");
        }
    }

    public List<MovieCharacterResponseDto> getByName(String name) {
        List<MovieCharacter> characters = charactersRepository.findAllByName(name);

        if (characters.isEmpty()) {
            throw new EntityNotFoundException("No movie characters found with the name: " + name);
        }

        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }

    private MovieCharacterDataDto fetchData(String apiUrl) {
        try {
            return restTemplate.getForObject(apiUrl, MovieCharacterDataDto.class);
        } catch (RestClientException e) {
            throw new FetchDataFailedException("Failed to fetch data from API");
        }
    }

    private void processApiResponse(MovieCharacterDataDto response) {
        List<MovieCharacter> movieCharacterList = response.getResults().stream()
                .map(characterMapper::toModel)
                .toList();
        charactersRepository.saveAll(movieCharacterList);
    }
}
