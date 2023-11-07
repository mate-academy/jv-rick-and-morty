package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CharacterClientImpl implements CharacterClient {
    private static final String INITIAL_URL = "https://rickandmortyapi.com/api/character";
    private ObjectMapper objectMapper;
    private CharacterRepository repository;

    @PostConstruct
    private void init() {
        List<Character> characters = getCharacters();
        repository.saveAll(characters);
    }

    public List<Character> getCharacters() {
        URI uri = URI.create(INITIAL_URL);
        CharacterResponseDto characterResponseDto = getCharactersFromSinglePage(uri);
        if (characterResponseDto == null || characterResponseDto.getResults().isEmpty()) {
            throw new RuntimeException("Occurred an error while pulling characters.");
        }
        List<Character> characters = new ArrayList<>(characterResponseDto.getResults());
        String next = characterResponseDto.getInfo().getNext();
        while (next != null) {
            CharacterResponseDto nextDto = getCharactersFromSinglePage(URI.create(next));
            characters.addAll(nextDto.getResults());
            next = nextDto.getInfo().getNext();
        }
        return characters;
    }

    private CharacterResponseDto getCharactersFromSinglePage(URI uri) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri).build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't send HTTP request " + e);
        }
    }
}
