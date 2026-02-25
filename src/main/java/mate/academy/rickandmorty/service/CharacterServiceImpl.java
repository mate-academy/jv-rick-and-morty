package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiResponseDto;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    @Override
    public void loadDataFromApi() {
        if (characterRepository.count() > 0) {
            return;
        }
        String url = API_URL;

        while (url != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            try {
                HttpResponse<String> response = httpClient
                        .send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200) {
                    System.err.println("API request failed with status: "
                            + response.statusCode());
                    break;
                }

                ApiResponseDto apiResponse = objectMapper.readValue(
                        response.body(), ApiResponseDto.class);

                List<Character> characters = apiResponse.getResults().stream()
                        .map(characterMapper::toModel)
                        .collect(Collectors.toList());
                characterRepository.saveAll(characters);

                url = apiResponse.getInfo().getNext();

                Thread.sleep(200);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to load characters from API", e);
            }
        }
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long count = characterRepository.count();
        if (count == 0) {
            throw new IllegalStateException("No characters found");
        }

        int random = new Random().nextInt((int) count);

        Character character = characterRepository
                .findAll(PageRequest.of(random, 1))
                .getContent()
                .get(0);

        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
