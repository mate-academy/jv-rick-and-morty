package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterInfoDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickMortyClient {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    
    public List<CharacterInfoDto> getAllCharacters() {
        List<CharacterInfoDto> characters = new ArrayList<>();
        String url = CHARACTER_URL;
        
        while (url != null) {
            CharacterResponseDto page = fetchPage(url);
            characters.addAll(page.getResults());
            url = page.getInfo().next();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return characters;
    }
    
    private CharacterResponseDto fetchPage(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Cant get response from API", e);
        }
    }
}
