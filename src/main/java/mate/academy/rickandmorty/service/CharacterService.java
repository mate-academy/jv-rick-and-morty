package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private static final String API = "https://rickandmortyapi.com/api/character";

    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public void loadAllCharacters() {
        if (characterRepository.count() > 0) {
            return;
        }
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = API;

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
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Fetching data failed", e);
        }
        System.out.println("Loading finished successfully");
    }

    public List<CharacterDto> getByName(String name) {
        return characterRepository.findByNameContainsIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    public Optional<CharacterDto> getRandomCharacter() {
        List<Long> allIds = characterRepository.findAllIds();
        if (allIds.isEmpty()) {
            return Optional.empty();
        }
        int random = new Random().nextInt(allIds.size());
        return characterRepository.findById(allIds.get(random))
                .map(characterMapper::toDto);
    }

}
