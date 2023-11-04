package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CharacterClient {
    private ObjectMapper objectMapper;

    public List<Character> getCharacters() {
        String initialUrl = "https://rickandmortyapi.com/api/character";
        CharacterResponseDto characterResponseDto = test(URI.create(initialUrl));
        List<Character> characters = new ArrayList<>(characterResponseDto.getResults());
        String next = characterResponseDto.getInfo().getNext();
        while (next != null) {
            CharacterResponseDto nextDto = test(URI.create(next));
            characters.addAll(nextDto.getResults());
            next = nextDto.getInfo().getNext();
        }
        return characters;
    }

    public CharacterResponseDto test(URI uri) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri).build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CharacterResponseDto characterResponseDto = objectMapper.readValue(response.body(), CharacterResponseDto.class);
            return characterResponseDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't send HTTP request " + e);
        }
    }
}
