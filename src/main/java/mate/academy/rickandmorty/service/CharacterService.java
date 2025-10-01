package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private static final String DEFAULT_API = "https://rickandmortyapi.com/api/";
    private static final String CHARACTER = "character";
    private static int countOfCharacters = 0;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public void loadAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = DEFAULT_API + CHARACTER;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ResponseDataDto responseDataDto = objectMapper
                    .readValue(response.body(), ResponseDataDto.class);
            List<Character> characterList = new ArrayList<>(responseDataDto.results().stream()
                    .map(characterMapper::toModel)
                    .toList());

            while (responseDataDto.info().next() != null) {
                url = responseDataDto.info().next();
                httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
                response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                responseDataDto = objectMapper.readValue(response.body(), ResponseDataDto.class);
                characterList.addAll(responseDataDto.results().stream()
                        .map(characterMapper::toModel)
                        .toList());
            }
            characterRepository.saveAll(characterList);
            countOfCharacters = responseDataDto.info().count();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Loading finished successfully");
    }

    public List<CharacterDto> getByName(String name) {
        return characterRepository.findByNameContainsIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    public CharacterDto getRandom() {
        int random = new Random().nextInt(0, countOfCharacters);
        return characterRepository.findById((long) random)
                .map(characterMapper::toDto)
                .orElse(null);
    }

}
