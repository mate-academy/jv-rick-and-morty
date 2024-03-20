package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import org.springframework.stereotype.Service;

@Service
public class CharacterClient {
    private static final String URL_BASE = "https://rickandmortyapi.com/api/character/";
    private final ObjectMapper mapper;
    private final Random random;

    public CharacterClient(ObjectMapper mapper) {
        this.mapper = mapper;
        this.random = new Random();
    }

    public CharacterResponseDto getRandomCharacterResponseDto() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_BASE))
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            JsonNode node = mapper.readTree(response.body());
            long numberOfCharacters = node.path("info").path("count").asLong();
            Long randomId = random.nextLong(numberOfCharacters) + 1;

            return getCharacterResponseDtoById(randomId);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to send a request " + request, e);
        }
    }

    public CharacterResponseDto getCharacterResponseDtoById(Long id) {
        String url = URL_BASE + id;
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), CharacterResponseDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to send a request " + request, e);
        }
    }
}
