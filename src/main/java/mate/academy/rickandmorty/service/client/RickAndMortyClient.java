package mate.academy.rickandmorty.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResultsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%s";
    private static final int PAGES = 42;
    private final ObjectMapper objectMapper;

    public RickAndMortyClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterResultsResponseDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<CharacterResultsResponseDto> characterDtos = new ArrayList<>();
        for (int i = 1; i <= PAGES; i++) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL.formatted(i)))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDto characterResponseDtos = objectMapper.readValue(response.body(),
                        CharacterResponseDto.class
                );
                characterDtos.addAll(characterResponseDtos.getResults());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get any character", e);
            }
        }
        return characterDtos;
    }
}
